using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace MemberZ.view
{
    class ConsoleMenu<T>
    {
        protected class MenuItem<G>
        {
            public G m_returnValue;
            public string m_text;

            public MenuItem(G a_returnValue, string a_text)
            {
                m_returnValue = a_returnValue;
                m_text = a_text;
            }
        }

        private List<MenuItem<T>> m_items = new List<MenuItem<T>>();

        public void AddItem(T a_returnValue, string a_text)
        {
            m_items.Add(new MenuItem<T>(a_returnValue, a_text));
        }

        public T DoMenu(string a_headline, T a_invalidChoise)
        {
            Console.Out.WriteLine("\t{0}", a_headline);
            Console.Out.WriteLine("-------------------------------------------");
            int i;
            for (i = 0; i < m_items.Count - 1; i++)
            {
                Console.Out.WriteLine("\t{0}. {1}", i + 1, m_items[i].m_text);
            }
            Console.Out.WriteLine("\n\t{0}. {1}", i + 1, m_items[i].m_text);

            Console.Out.Write("\n\n\tVal: ");
            string choise = Console.In.ReadLine();
            int choiceInt;
            if (Int32.TryParse(choise, out choiceInt))
            {
                for (i = 0; i < m_items.Count; i++)
                {
                    if (i + 1 == choiceInt)
                    {
                        return m_items[i].m_returnValue;
                    }
                }
            }

            return a_invalidChoise;
        }
    }
}
