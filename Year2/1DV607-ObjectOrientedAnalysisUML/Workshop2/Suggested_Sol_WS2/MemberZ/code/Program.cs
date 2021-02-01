using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace MemberZ
{
    class Program
    {
        static void Main(string[] args)
        {
            model.MemberRegistry mr = new model.MemberRegistry();
            mr.Load();
            view.MainView viewcore = new view.MainView();
            controller.Master controller = new controller.Master();



            while (controller.Do(mr, viewcore));

            mr.Save();
        }
    }
}
