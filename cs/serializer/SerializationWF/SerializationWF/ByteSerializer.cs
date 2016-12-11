using System;
using System.IO;
using System.Threading.Tasks;
using System.Runtime.Serialization.Formatters.Binary;

namespace SerializationWF
{
    public class ByteSerializer : AbstractSerializer
    {
        private BinaryFormatter binaryFormatter = new BinaryFormatter();

        public void Serialize(Object o)
        {
            using (FileStream fstream = new FileStream(@"C:\data\BinaryFile.dat", FileMode.Create))
            {
                binaryFormatter.Serialize(fstream, o);
            }
        }

        public Object Deserialize()
        {
            String path = @"C:\data\BinaryFile.dat";
            try
            {
                using (FileStream fstream = new FileStream(path, FileMode.Open))
                {
                    return binaryFormatter.Deserialize(fstream);
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
