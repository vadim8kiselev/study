#pragma once

template <class Type>  // class-template Stack
class Stack
{
private:

	class Node{
	public:
		Type value;
		Node* next;

		Node(Type value, Node* next){
			this->value = value;
			this->next = next;
		}
	};

	Node* head;	
	int sizeOfStack;  

public:

	Stack() : head(nullptr),sizeOfStack(0){}

	bool empty(){
		return head == nullptr;
	}
	
	void push(Type value){
		sizeOfStack++;
		head = new Node(value, head);
	}

	void pop(){		
		if (empty()) return /*0*/;	
		sizeOfStack--;
		//Type val = head->value;
		head = head->next;
		//return val;
	}
	
	Type top(){
		if (empty()) return 0;
		return head->value;
	}

	~Stack(){}
};

