#pragma once

template <class Type> // class-template Queue
class Queue
{
private:

	class Node{
	public:
		Type value;
		Node* next;

		Node(Type value){
			this->value = value;
			this->next = nullptr;
		}
	};

	Node* head;
	Node* tail;
	

public:

	Queue() : head(nullptr),tail(nullptr){}

	bool empty(){
		return head == nullptr;
	}

	void put(Type value){
		
		Node* ptr = tail;
		tail = new Node(value);

		if (head == nullptr)
			head = tail;
		else
			ptr->next = tail;
	}

	Type get(){
		if (empty()) return 0;
		Node* tmp = head;
		Type val = tmp->value;
		head = tmp->next;
		if (head == nullptr)
			tail = nullptr;
		delete tmp;
		return val;
	}

	Type show(){
		if (empty()) return 0;
		return head->value;
	}
	
	~Queue(){}
};

