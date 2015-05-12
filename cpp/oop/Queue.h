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
	int sizeOfQueue;

public:

	Queue() : head(nullptr),tail(nullptr), sizeOfQueue(0){}

	bool empty(){
		return head == nullptr;
	}

	void put(Type value){
		sizeOfQueue++;
		Node* ptr = tail;
		tail = new Node(value);

		if (head == nullptr)
			head = tail;
		else
			ptr->next = tail;
	}

	void get(){
		if (empty()) return /*0*/;
		sizeOfQueue--;
		//Type val = head->value;
		head = head->next;
		if (head == nullptr)
			tail = nullptr;
		//return val;
	}

	Type show(){
		if (empty()) return 0;
		return head->value;
	}
	
	~Queue(){}
};

