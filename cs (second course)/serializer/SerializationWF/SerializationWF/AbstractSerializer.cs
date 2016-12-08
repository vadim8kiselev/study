using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace SerializationWF
{
    public interface AbstractSerializer
    {
        void Serialize(Object o);

        Object Deserialize();
    }
}
