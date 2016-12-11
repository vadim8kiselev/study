using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Xml.Serialization;

namespace SerializationWF
{
    [XmlRoot("Cat")]
    [Serializable]
    public class Cat: Animal
    {
        public Cat() : base() { }

        public Cat(String name, Int32 height, Int32 weight) : base(name, height, weight) { }

        public override String GetTypeName()
        {
            return "Cat";
        }
    }
}
