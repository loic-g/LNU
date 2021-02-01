using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace MemberZ.controller
{
    class Master
    {


        public bool Do(model.MemberRegistry a_model, view.MainView a_view)
        {
            model.Member selectedMember = a_view.GetSelectedMember(a_model);
            model.Boat selectedBoat = a_view.GetSelectedBoat(a_model);

            if (selectedMember == null)
            {
                view.MainView.MainMenuChoice choice;
                choice = a_view.DoMainMenu();

                switch (choice)
                {
                    case view.MainView.MainMenuChoice.AddMember:
                        DoAddMember(a_model, a_view);
                        return true;
                    case view.MainView.MainMenuChoice.ListMembers:
                        a_view.PrintMemberList(a_model.GetMembers());
                        return true;
                    case view.MainView.MainMenuChoice.ListMembersFull:
                        a_view.PrintFullMemberList(a_model.GetMembers());
                        return true;

                    case view.MainView.MainMenuChoice.SelectMember:
                        model.Member m = a_view.DoSelectMember(a_model.GetMembers());
                        if (m != null)
                        {
                            a_view.SetSelectedMember(m);
                        }
                        return true;
                    case view.MainView.MainMenuChoice.Quit:
                    default:
                        return false;
                }
            }
            else
            {
                if (selectedBoat != null)
                {
                    DoSelectedBoat(a_view, selectedMember, selectedBoat);
                }
                else
                {
                    DoSelectedMember(a_model, a_view, selectedMember);
                }

                return true;
            }
        }

        private void DoSelectedMember(model.MemberRegistry a_model, view.MainView a_view, model.Member selectedMember)
        {
            view.MemberView.MemberMenuChoice choice;
            choice = a_view.m_memberView.DoMemberMenu(selectedMember);
            switch (choice)
            {
                case view.MemberView.MemberMenuChoice.AddBoat:
                        DoAddBoat(a_view, selectedMember);
                    break;
                case view.MemberView.MemberMenuChoice.DeleteMember:
                        DoDeleteMember(a_model, a_view, selectedMember);
                    break;
                case view.MemberView.MemberMenuChoice.ChangeMember:
                    DoChangeMember(a_model, a_view, selectedMember);
                    break;
                case view.MemberView.MemberMenuChoice.SelectBoat:
                        model.Boat b;
                        b = a_view.m_memberView.m_boatView.DoSelectBoatForm(selectedMember.GetBoats());
                        if (b != null)
                        {
                            a_view.SetSelectedBoat(selectedMember, b);
                        }
                    break;
                case view.MemberView.MemberMenuChoice.Back:
                default:
                    a_view.SetSelectedMember(null);
                break;
            }
        }

        private void DoChangeMember(model.MemberRegistry a_model, view.MainView a_view, model.Member selectedMember)
        {
            model.Member m;
            m = a_view.m_memberView.DoChangeMemberForm(selectedMember);
            a_model.UpdateMember(selectedMember, m);
        }

        private void DoDeleteMember(model.MemberRegistry a_model, view.MainView a_view, model.Member selectedMember)
        {
            a_model.DeleteMember(selectedMember);
            a_view.SetSelectedMember(null);
        }

        private void DoAddBoat(view.MainView a_view, model.Member selectedMember)
        {
            model.Boat b = a_view.m_memberView.m_boatView.DoAddBoatForm();
            if (b != null)
            {
                selectedMember.AddBoat(b);
                a_view.SetSelectedBoat(selectedMember, b);
            }
        }

        private void DoSelectedBoat(view.MainView a_view, model.Member selectedMember, model.Boat selectedBoat)
        {
            view.BoatView.BoatMenuChoice choice;
            choice = a_view.m_memberView.m_boatView.DoBoatMenu(selectedBoat);
            switch (choice)
            {
                case view.BoatView.BoatMenuChoice.ChangeBoat:
                    DoChangeBoat(a_view, selectedMember, selectedBoat);
                break;
                case view.BoatView.BoatMenuChoice.DeleteBoat:
                    DoDeleteBoat(a_view, selectedMember, selectedBoat);
                break;
                default:
                    a_view.SetSelectedBoat(null, null);
                break;
            }
        }

        private static void DoDeleteBoat(view.MainView a_view, model.Member selectedMember, model.Boat selectedBoat)
        {
            selectedMember.RemoveBoat(selectedBoat);
            a_view.SetSelectedBoat(null, null);
        }

        private static void DoChangeBoat(view.MainView a_view, model.Member selectedMember, model.Boat selectedBoat)
        {
            model.Boat b;
            b = a_view.m_memberView.m_boatView.DoChangeBoatForm(selectedBoat);
            if (b != null)
            {
                selectedMember.UpdateBoat(selectedBoat, b);
            }
        }

        private void DoAddMember(model.MemberRegistry a_model, view.MainView a_view)
        {
            model.Member m = a_view.m_memberView.DoAddMemberForm();
            if (m != null) {
                a_model.AddMember(m);
                a_view.SetSelectedMember(m);
            }
        }
    }
}
