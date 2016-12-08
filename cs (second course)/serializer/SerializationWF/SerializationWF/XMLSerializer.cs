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
            String path = @"C:\data\XMLFile.xml";
            try
            {
                using (FileStream fstream = new FileStream(path, FileMode.Open))
                {
                    return xmlSerializer.Deserialize(fstream);
                }
            }
            catch (System.IO.FileNotFoundException exception)
            {
                // sorry
                return null;
            } finally
            {
                File.Delete(path);
            }
        }
    }
}
