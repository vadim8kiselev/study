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
	 

public:

	Stack() : head(nullptr){}

	bool empty(){
		return head == nullptr;
	}
	
	void push(Type value){
		
		head = new Node(value, head);
	}

	Type pop(){		
		if (empty()) return 0;	
		
		Node* tmp = head;
		Type val = tmp->value;
		head = tmp->next;
		delete tmp;
		return val;
		
	}
	
	Type top(){
		if (empty()) return 0;
		return head->value;
	}

	~Stack(){}
};

