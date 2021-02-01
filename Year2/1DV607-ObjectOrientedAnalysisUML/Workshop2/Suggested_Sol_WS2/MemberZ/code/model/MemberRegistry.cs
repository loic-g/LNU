using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Xml;

namespace MemberZ.model
{
    class MemberRegistry
    {
        private List<Member> m_members;
        private int m_id;

        public MemberRegistry()
        {
            m_members = new List<Member>();
            m_id = 0;
        }

        public void AddMember(Member a_member)
        {
            m_members.Add(new Member(a_member, m_id));
            m_id++;
        }

        private void InsertMember(Member a_member)
        {
            if (m_id <= a_member.GetId())
            {
                m_id = a_member.GetId() + 1;
            }
            m_members.Add(a_member);
        }

        public void DeleteMember(Member a_member)
        {
            m_members.Remove(a_member);
        }

        public Member GetMember(int a_id)
        {
            foreach (Member m in m_members)
            {
                if (m.GetId() == a_id)
                {
                    return m;
                }
            }

            return null;
        }

        public void UpdateMember(Member a_inRegistry, Member a_newMember)
        {
            a_inRegistry.Set(a_newMember);
        }

        public IEnumerable<Member> GetMembers()
        {
            return m_members;
        }

        public void Load()
        {

            using (XmlTextReader xml = new XmlTextReader("members.xml"))
            {
                while (xml.Read()) {
                    if (xml.IsStartElement()) {
                        switch (xml.Name)
                        {
                            case "Member":
                                Member m = LoadMember(xml.ReadSubtree());
                                if (m != null) 
                                {
                                    InsertMember(m);
                                }

                            break;
                            
                        }
                    }
                }
            }
        }

        private Boat LoadBoat(XmlReader a_xml)
        {
            Boat.Type type = Boat.Type.BT_Count;
            double length = 0;
            while (a_xml.Read()) {
                if (a_xml.IsStartElement()) {
                    switch (a_xml.Name) {
                        case "type":
                            int t;
                            Int32.TryParse(a_xml.ReadString(), out t);
                            type = (Boat.Type)t;
                        break;
                        case "length":
                            double.TryParse(a_xml.ReadString(), out length);
                        break;
                    }
                }
            }

            if (type != Boat.Type.BT_Count && length != 0)
            {
                return new Boat(type, length);
            }
            return null;
        }

        private Member LoadMember(XmlReader a_xml) {
            string lname = "", fname = "", phone = "";
            int id = -1;
            List<Boat> boats = new List<Boat>();
            while (a_xml.Read()) {
                if (a_xml.IsStartElement()) {
                    switch (a_xml.Name) {
                        case "fname":
                            fname = a_xml.ReadString();
                        break;
                        case "lname":
                            lname = a_xml.ReadString();
                        break;
                        case "phone":
                            phone = a_xml.ReadString();
                        break;
                        case "id":
                            Int32.TryParse(a_xml.ReadString(), out id);
                        break;
                        case "Boat":
                            Boat b = LoadBoat(a_xml.ReadSubtree());
                            if (b != null)
                            {
                                boats.Add(b);
                            }
                        break;
                    }
                }
            }

            if (fname != "" && lname != "" && phone != "" && id != -1)
            {                                    
                Member m = new Member(fname, lname, phone, id);
                foreach (Boat b in boats)
                {
                    m.AddBoat(b);
                }
                return m;
            }
            return null;
        }

        public void Save()
        {
            using (XmlTextWriter xml = new XmlTextWriter("members.xml", null))
            {
                xml.WriteStartDocument(); xml.WriteWhitespace("\n");
                xml.WriteStartElement("Members"); xml.WriteWhitespace("\n");
                foreach (Member m in m_members)
                {
                    xml.WriteWhitespace("\t"); xml.WriteStartElement("Member"); xml.WriteWhitespace("\n");
                    xml.WriteWhitespace("\t\t"); xml.WriteElementString("fname", m.GetFirstName()); xml.WriteWhitespace("\n");
                    xml.WriteWhitespace("\t\t"); xml.WriteElementString("lname", m.GetLastName()); xml.WriteWhitespace("\n");
                    xml.WriteWhitespace("\t\t"); xml.WriteElementString("id", m.GetId().ToString()); xml.WriteWhitespace("\n");
                    xml.WriteWhitespace("\t\t"); xml.WriteElementString("phone", m.GetPhone()); xml.WriteWhitespace("\n");
                    
                    foreach (Boat b in m.GetBoats()) {
                        xml.WriteWhitespace("\t\t"); xml.WriteStartElement("Boat"); xml.WriteWhitespace("\n");
                        xml.WriteWhitespace("\t\t\t"); xml.WriteElementString("type", ((int)b.GetTypeOfBoat()).ToString()); xml.WriteWhitespace("\n");
                        xml.WriteWhitespace("\t\t\t"); xml.WriteElementString("length", b.GetLength().ToString()); xml.WriteWhitespace("\n");
                        xml.WriteWhitespace("\t\t"); xml.WriteEndElement(); xml.WriteWhitespace("\n");
                    }
                    
                    xml.WriteWhitespace("\t"); xml.WriteEndElement(); xml.WriteWhitespace("\n");

                }

                xml.WriteEndElement(); xml.WriteWhitespace("\n");
                xml.WriteEndDocument(); xml.WriteWhitespace("\n");
            }
        }
    }
}
