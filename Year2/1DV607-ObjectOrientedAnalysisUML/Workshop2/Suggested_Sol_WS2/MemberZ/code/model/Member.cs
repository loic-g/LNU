using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace MemberZ.model
{
    class Member
    {
        private string m_firstName;
        private string m_lastName;
        private int m_id;
        private string m_phone;
        private List<Boat> m_boats = new List<Boat>();

        public Member()
        {
            m_id = -1;
            m_firstName = "John";
            m_lastName = "Doe";
            m_phone = "123456";
        }

        public Member(string a_firstName, string a_lastName, string a_phoneNr, int a_id = -1)
        {
            m_id = a_id;
            m_firstName = a_firstName;
            m_lastName = a_lastName;
            m_phone = a_phoneNr;
        }

        public Member(Member a_copy, int a_id)
        {
            m_id = a_id;
            Set(a_copy);
        }

        public void Set(Member a_copy)
        {
            m_firstName = a_copy.m_firstName;
            m_lastName = a_copy.m_lastName;
            m_phone = a_copy.m_phone;
        }

        public void AddBoat(Boat a_boat) {
            m_boats.Add(a_boat);
        }

        public void RemoveBoat(Boat a_boat)
        {
            m_boats.Remove(a_boat);
        }

        public void UpdateBoat(Boat a_inMember, Boat a_newBoat)
        {
            a_inMember.Set(a_newBoat);
        }

        public Boat GetBoat(int a_index)
        {
            if (m_boats.Count > a_index && a_index >= 0)
            {
                return m_boats[a_index];
            }
            return null;
        }

        public IEnumerable<Boat> GetBoats()
        {
            return m_boats;
        }

        public int GetId() {
            return m_id;
        }

        public string GetFirstName()
        {
            return m_firstName;
        }

        public string GetLastName()
        {
            return m_lastName;
        }

        public string GetPhone()
        {
            return m_phone;
        }

    }
}
