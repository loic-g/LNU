using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace MemberZ.view
{
    class View
    {

        protected bool DoFormField(out string a_value, string a_default, string a_text)
        {
            Console.Out.Write("\t{0}: ", a_text);
            System.Windows.Forms.SendKeys.SendWait(a_default);
            a_value = Console.In.ReadLine();
            if (a_value == "")
            {
                return false;
            }
            return true;
        }

        protected T DoMenu<T>(String[] a_strings, T a_invalid, String a_menuText)
        {
            ConsoleMenu<T> menu = new ConsoleMenu<T>();
            int i = 0;
            foreach (T val in Enum.GetValues(typeof(T)))
            {
                if (!Enum.Equals(val, a_invalid)) {
                    menu.AddItem(val, a_strings[i]);
                }
                i++;
            }

            return menu.DoMenu(a_menuText, a_invalid);
        }


    }
}
