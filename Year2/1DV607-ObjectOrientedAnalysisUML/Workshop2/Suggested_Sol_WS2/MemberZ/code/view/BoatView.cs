using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace MemberZ.view
{
    class BoatView : View
    {
        public enum BoatMenuChoice
        {
            ChangeBoat,
            DeleteBoat,
            Back,
            Invalid
        }

        private string BoatTypeToString(model.Boat.Type a_type)
        {
            String[] types = new String[(int)model.Boat.Type.BT_Count] { "Motorbåt", "Segelbåt", "Motorseglare", "Kanot/Kajak", "Övrigt" };
            return types[(int)a_type];
        }

        public BoatMenuChoice DoBoatMenu(model.Boat a_boat)
        {
            Console.Clear();
            Console.Out.WriteLine("Båt");
            Console.Out.WriteLine("Typ: {0}\tLängd:{1}", BoatTypeToString(a_boat.GetTypeOfBoat()), a_boat.GetLength());


            String[] menuStrings = new String[] { "Ändra", "Ta Bort", "Tillbaka"};

            return DoMenu<BoatMenuChoice>(menuStrings, BoatMenuChoice.Invalid, "Båtmeny");
        }

        public void PrintBoatList(IEnumerable<model.Boat> a_boats)
        {
            int i = 0;
            foreach (model.Boat b in a_boats)
            {
                Console.Out.WriteLine("{0}\t{1} \t{2}m", i, BoatTypeToString(b.GetTypeOfBoat()), b.GetLength());
                i++;
            }
        }


        public model.Boat DoSelectBoatForm(IEnumerable<model.Boat> a_boats)
        {
            Console.Out.WriteLine("\tVälj Båt via Index");
            string choise = Console.In.ReadLine();
            int choiceInt;
            if (Int32.TryParse(choise, out choiceInt))
            {
                int i = 0;
                foreach (model.Boat b in a_boats)
                {
                    if (choiceInt == i)
                    {
                        return b;
                    }
                    i++;
                }
            }
            return null;
        }

        private bool DoBoatTypeField(out model.Boat.Type a_value, model.Boat.Type a_default)
        {
            Console.Out.WriteLine("Välj Båttyp");

            for (int i = 0; i < (int)model.Boat.Type.BT_Count; i++)
            {
                Console.Out.WriteLine("\t{0} för {1}", i, BoatTypeToString((model.Boat.Type)i));
            }
            Console.Write("Val: ");

            string choice;
            DoFormField(out choice, ((int)a_default).ToString(), "Val: ");
            int choiceInt;
            if (Int32.TryParse(choice, out choiceInt))
            {
                if (choiceInt >= 0 && choiceInt < (int)model.Boat.Type.BT_Count)
                {
                    a_value = (model.Boat.Type)choiceInt;
                    return true;
                }
            }

            a_value = model.Boat.Type.BT_Count;
            return false;
        }

        public model.Boat DoAddBoatForm()
        {
            Console.Out.WriteLine("Lägg Till Ny Båt");
            return DoBoatForm(null);
        }

        private model.Boat DoBoatForm(model.Boat a_defaultValues)
        {
            string lengthStr = "";
            model.Boat.Type boatType = model.Boat.Type.BT_Motor;
            if (a_defaultValues != null)
            {
                boatType = a_defaultValues.GetTypeOfBoat();
                lengthStr = Convert.ToString(a_defaultValues.GetLength());
            };

            if (DoBoatTypeField(out boatType, boatType) != true ||
                DoFormField(out lengthStr, lengthStr, "Längd") != true)
            {
                return null;
            }

            double length;
            if (Double.TryParse(lengthStr, out length) != true)
            {
                return null;
            }

            return new model.Boat(boatType, length);
        }

        public model.Boat DoChangeBoatForm(model.Boat a_defaultValues)
        {
            Console.Out.WriteLine("Ändra Båt");
            return DoBoatForm(a_defaultValues);
        }
    }
}
