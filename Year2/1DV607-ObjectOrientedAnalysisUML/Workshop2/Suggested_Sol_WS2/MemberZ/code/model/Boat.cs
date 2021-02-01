using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace MemberZ.model
{
    class Boat
    {
        public enum Type
        {
            BT_Motor = 0,
            BT_Sail,
            BT_MotorSail,
            BT_Canoe,
            BT_Other,
            BT_Count
        }

        private Type m_type;
        private double m_length;

        public Boat(Type a_type, double a_length)
        {
            m_length = a_length;
            m_type = a_type;
        }

        public double GetLength() {
            return m_length;
        }

        public Type GetTypeOfBoat()
        {
            return m_type;
        }

        public void Set(Boat a_copy)
        {
            m_type = a_copy.m_type;
            m_length = a_copy.m_length;
        }
    }
}
