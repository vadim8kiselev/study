template <class Type> 
class doubleList {
        class Node {
        public:
                Type value;
                Node *next, *prev;
                Node(Type x) : value(x), next(nullptr), prev(nullptr) { }
        };
        
        Node *head, *tail;
        int size;
        
        Node *find(int index) {
                if ((index < 1) || (index > size))   return nullptr;                
                else {  Node *cur = head;
                        for (int i = 1; i < index; i++)
                                cur = cur->next;                        
                        return cur;
                }
        } 
public:
doubleList():head(nullptr),tail(nullptr),size(0){
} 

~doubleList() {
while (!empty()) remove(1); 
}

bool empty() {
return head == nullptr; 
}

int getLength() {
return size; 
}

 Type get(int index) {
  if ((index < 1) || (index > size)) {
   throw DoubleListException("DListException: get");
                        return nullptr;
  } 
else {
  Node *r = find(index);
   Type i = r->value;
   return i;
}
}
void insert(int index, Type data,bool flag) {
  if ((index < 1) || (index > size + 1)) {
    throw DoubleListException("dListException:insert");
  }
  else{
    Node *newPtr = new Node(data);
    size = getLength() + 1;
    Node *cur = find(index);
    if (cur == nullptr) {
        head = newPtr;
        tail = newPtr;
    }
    else {
        if (!flag){
            newPtr->next = cur;
            newPtr->prev = cur->prev;
            cur->prev = newPtr;
            if (cur == head) 
                 head = newPtr;
            else newPtr->prev->next = newPtr;
        }
        else{
            newPtr->next = cur->next;
            newPtr->prev = cur;
            cur->next = newPtr;
          if (cur == tail)
                  tail = newPtr;
          else
                  newPtr->next->prev = newPtr;
        }
    }
  }
       
 void remove(int index) {
   if ((index < 1) || (index > size)) {
   throw DoubleListException("doubleListException");
   }
   else {
      Node *cur = find(index);
      --size;
      if (size == 0) {
          head = nullptr;
          tail = nullptr;
      }
      else
           if (cur == head){
               head = head->next;
               head->prev = nullptr;
            }
            else if (cur == tail) {
                 tail = tail->prev;
                 tail->next = nullptr;
            }
            else { 
                 cur->prev->next = cur->next;
                 cur->next->prev = cur->prev;
            }
             cur->next = nullptr;
             cur->prev = nullptr;
             delete cur;
           }
  }
};
