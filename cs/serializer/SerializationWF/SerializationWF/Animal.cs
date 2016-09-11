using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Xml.Serialization;

namespace SerializationWF
{
    [XmlInclude(typeof(Cat))]
    [XmlInclude(typeof(Dog))]
    [Serializable]
    public abstract class Animal
    {
        public String name { get; set; }
        public Int32 height { get; set; }
        public Int32 weight { get; set; }

        public Animal() { }

        public Animal(String name, Int32 height, Int32 weight)
        {
            this.name = name;
            this.height = height;
            this.weight = weight;
        }

        public abstract String GetTypeName();
    }
}
