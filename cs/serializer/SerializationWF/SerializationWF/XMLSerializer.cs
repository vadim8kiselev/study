using System;
using System.IO;
using System.Xml.Serialization;

namespace SerializationWF
{
    public class XMLSerializer : AbstractSerializer
    {
        private XmlSerializer xmlSerializer = new XmlSerializer(typeof(Animal));

        public void Serialize(Object o)
        {
            using (FileStream fstream = new FileStream(@"C:\data\XMLFile.xml", FileMode.Create))
            {
                xmlSerializer.Serialize(fstream, o);
            }
        }

        public Object Deserialize()
        {
            try
            {
                using (FileStream fstream = new FileStream(@"C:\data\XMLFile.xml", FileMode.Open))
                {
                    return xmlSerializer.Deserialize(fstream);
                }
            }
            catch (System.IO.FileNotFoundException exception)
            {
                // sorry
                return null;
            }
        }
    }
}
