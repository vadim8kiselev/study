template <class Type> 
class list
{
        class Node
        {
        public:
                Type value;
                Node* next;
                Node(Type x) : value(x), next(0) { }
        };
        Node* head;
        int size;
        
        Node* find(int index)
        {
                if ((index < 1) || (index > size))  {
                        throw ListException(“List:find - OutOfRangeException”); return nullptr;
                }               
                Node* cur = head;
                for (int i = 1; i < index; i++)
                     cur = cur->next;
                                
        }
public:
        list() : head(0), size(0) {
        }

        ~list() {
        while (!empty()) remove(1); 
        }

        bool empty() { 
        return head == 0; 
        }

        int getLength() {
        return size;
        }

void insert(int index, Type data) {
      if ((index < 1) || (index > size + 1))  {
          throw ListException("ListException: insert");
          return 0;
      }                
           Node* newPtr = new Node(data);
           size = getLength() + 1;
           if (index == 1)  {
               newPtr->next = head;
               head = newPtr;
           }
           else  {
               Node* prev = find(index - 1);
               newPtr->next = prev->next;
               prev->next = newPtr;
           }                
}
Type get(int index) {
    if ((index < 1) || (index > size))   {
       throw ListException("ListException: get");
       return 0;
    }
        Node* r = find(index);
        Type i = r->inf;
        return i;                
}
void remove(int index) {
     if ((index < 1) || (index > size))  {
         throw ListException("ListException: remove ");
         return 0;
     }
     else {
         Node* cur;
         --size;
         if (index == 1)  {
                 cur = head;
                  head = head->next;
         }
         else {
                   Node* prev = find(index - 1);
                   cur = prev->next;
                   prev->next = cur->next;
         }
                    cur->next = nullptr;
                    delete cur;
         }
}
void print() {
   for (Node* cur = head; cur != nullptr; cur = cur->next) 
                     out << cur->inf << " ";  } 
};
