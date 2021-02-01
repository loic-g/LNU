using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace MemberZ.view
{
    class MainView : View
    {

        public int m_selectedMemberId = -1;
        public int m_selectedBoatIx = -1;

        public enum MainMenuChoice
        {
            AddMember,
            ListMembers,
            ListMembersFull,
            SelectMember,
            Quit,
            Invalid
        }

        public MemberView m_memberView = new MemberView();
       

        public void SetSelectedMember(model.Member a_member) {
            m_selectedBoatIx = -1;
            if (a_member != null)
            {
                m_selectedMemberId = a_member.GetId();
            }
            else
            {
                m_selectedMemberId = -1;
            }
        }

        public void SetSelectedBoat(model.Member a_member, model.Boat a_boat) 
        {
            m_selectedBoatIx = -1;
            if (a_member == null || a_boat == null)
            {
                return;
            }
            for (int ix = 0; ; ix++)
            {
                model.Boat b = a_member.GetBoat(ix);
                if (b == a_boat)
                {
                    m_selectedBoatIx = ix;
                    return;
                }
                else if (b == null)
                {
                    return;
                }
            }
        }

        public model.Member GetSelectedMember(model.MemberRegistry a_registry)
        {
            if (m_selectedMemberId >= 0)
            {
                return a_registry.GetMember(m_selectedMemberId);
            }
            return null;
        }

        public model.Boat GetSelectedBoat(model.MemberRegistry a_registry)
        {
            model.Member selectedMember;
            selectedMember = GetSelectedMember(a_registry);
            if (selectedMember != null)
            {
                return selectedMember.GetBoat(m_selectedBoatIx);
            }
            
            return null;
        }

        private void PrintMemberListInternal(IEnumerable<model.Member> a_members) {
            Console.Clear();
            Console.Out.WriteLine("Medlemsnummer\tNamn\t\t\tAntalBåtar");
            foreach (model.Member m in a_members)
            {
                Console.Out.WriteLine("{0}\t\t{1} {2}\t\t{3}", m.GetId(), m.GetFirstName(), m.GetLastName(), m.GetBoats().Count());
            }
        }

        public void PrintMemberList(IEnumerable<model.Member> a_members)
        {
            PrintMemberListInternal(a_members);
            Console.Out.Write("Tryck return för att återgå till huvudmenyn");
            Console.In.ReadLine();
        }

        public void PrintFullMemberList(IEnumerable<model.Member> a_members)
        {
            Console.Clear();
             foreach (model.Member m in a_members)
            {
                m_memberView.PrintMember(m);
                Console.Out.WriteLine("===========================================");
                Console.Out.WriteLine("");
            }
            Console.Out.Write("Tryck return för att återgå till huvudmenyn");
            Console.In.ReadLine();
        }

        public model.Member DoSelectMember(IEnumerable<model.Member> a_members)
        {
            PrintMemberListInternal(a_members);
            Console.Out.Write("Välj en medlem via medlemsnummer: ");
            string choise = Console.In.ReadLine();
            int memberid;

            if (Int32.TryParse(choise, out memberid))
            {
                foreach (model.Member m in a_members)
                {
                    if (m.GetId() == memberid)
                    {
                        return m;
                    }
                }
            }

            return null;
        }



        public MainMenuChoice DoMainMenu()
        {
            Console.Clear();
            Console.Out.WriteLine("\tMemberZ 1.0 Beta\n\n");
            String[] menuStrings = new String[] {"Lägg Till Ny Medlem", "Enkel Medlemslista", "Fullständing Medlemslista", "Välj Medlem", "Avsluta"};

            return DoMenu<MainMenuChoice>(menuStrings, MainMenuChoice.Invalid, "Huvudmeny");
        }
    }
}
