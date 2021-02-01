using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace MemberZ.view
{
    class MemberView : View
    {
        public BoatView m_boatView = new BoatView();

        public enum MemberMenuChoice
        {
            
            AddBoat,
            SelectBoat,
            ChangeMember,
            DeleteMember,
            Back,
            Invalid,
        }

        public MemberMenuChoice DoMemberMenu(model.Member a_member)
        {
            Console.Clear();
            Console.Out.WriteLine("Medlem");
            PrintMember(a_member);

            String[] menuStrings = new String[] { "Lägg Till Ny Båt", "Välj Båt", "Ändra", "Ta Bort", "Tillbaka" };

            return DoMenu<MemberMenuChoice>(menuStrings, MemberMenuChoice.Invalid, "Medlemsmeny");
        }

        public void PrintMember(model.Member a_member)
        {
            Console.Out.WriteLine("Namn:\t\t{0} {1}", a_member.GetFirstName(), a_member.GetLastName());
            Console.Out.WriteLine("Medlemsnummer:\t{0}", a_member.GetId());
            Console.Out.WriteLine("Telefonnummer:\t{0}", a_member.GetPhone());

            if (a_member.GetBoats().Count() > 0)
            {
                Console.Out.WriteLine("\t\nRegistrerade Båtar");
                Console.Out.WriteLine("-------------------------------------------");
                Console.Out.WriteLine("Nr.\tTyp\tLängd");
            }
            m_boatView.PrintBoatList(a_member.GetBoats());
        }

        public model.Member DoAddMemberForm()
        {
            Console.Clear();
            Console.Out.WriteLine("\tLägg In Ny Medlem");

            return DoMemberForm(null);
        }

        public model.Member DoChangeMemberForm(model.Member a_defaultValues)
        {
            Console.Clear();
            Console.Out.WriteLine("\tÄndra Medlem");
            return DoMemberForm(a_defaultValues);

        }





        private model.Member DoMemberForm(model.Member a_defaultValues)
        {
            string firstName = "", lastName = "", phoneNr = "";
            if (a_defaultValues != null) {
                firstName = a_defaultValues.GetFirstName();
                lastName = a_defaultValues.GetLastName();
                phoneNr = a_defaultValues.GetPhone();
            };

            if (DoFormField(out firstName, firstName, "Förnamn") != true ||
                DoFormField(out lastName, lastName, "Efternamn") != true ||
                DoFormField(out phoneNr, phoneNr, "Telefon") != true)
            {
                return null;
            }

            return new model.Member(firstName, lastName, phoneNr);
        }


    }
}
