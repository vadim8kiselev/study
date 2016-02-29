#pragma once

namespace GForm {

	using namespace System;
	using namespace System::ComponentModel;
	using namespace System::Collections;
	using namespace System::Windows::Forms;
	using namespace System::Data;
	using namespace System::Drawing;

	public ref class Form1 : public System::Windows::Forms::Form
	{
	public:
		Form1(void)
		{
			InitializeComponent();
		}

	protected:
		~Form1()
		{
			if (components)
			{
				delete components;
			}
		}

	private: System::ComponentModel::Container ^components;
	private: System::Windows::Forms::OpenFileDialog^  openFileDialog;
	private: System::Windows::Forms::Button^  btnOpen;

	private: System::Collections::Generic::List<line> lines;

#pragma region Windows Form Designer generated code
		void InitializeComponent(void)
		{
			this->openFileDialog = (gcnew System::Windows::Forms::OpenFileDialog());
			this->btnOpen = (gcnew System::Windows::Forms::Button());
			this->SuspendLayout();
			// 
			// openFileDialog
			// 
			this->openFileDialog->DefaultExt = L"txt";
			this->openFileDialog->FileName = L"openFileDialog1";
			this->openFileDialog->Filter = L"Текстовые файлы (*.txt)|*.txt|Все файлы (*.*)|*.*";
			this->openFileDialog->Title = L"Open file";
			this->openFileDialog->FileOk += gcnew System::ComponentModel::CancelEventHandler(this, &Form1::openFileDialog1_FileOk);
			// 
			// btnOpen
			// 
			this->btnOpen->Location = System::Drawing::Point(12, 12);
			this->btnOpen->Name = L"btnOpen";
			this->btnOpen->Size = System::Drawing::Size(131, 38);
			this->btnOpen->TabIndex = 0;
			this->btnOpen->Text = L"Open file";
			this->btnOpen->UseVisualStyleBackColor = true;
			this->btnOpen->Click += gcnew System::EventHandler(this, &Form1::btnOpen_Click);
			// 
			// Form1
			// 
			this->AutoScaleDimensions = System::Drawing::SizeF(6, 13);
			this->AutoScaleMode = System::Windows::Forms::AutoScaleMode::Font;
			this->ClientSize = System::Drawing::Size(587, 523);
			this->Controls->Add(this->btnOpen);
			this->KeyPreview = true;
			this->Name = L"Form1";
			this->Text = L"Form1";
			this->Load += gcnew System::EventHandler(this, &Form1::Form1_Load);
			this->Paint += gcnew System::Windows::Forms::PaintEventHandler(this, &Form1::Form1_Paint);
			this->KeyDown += gcnew System::Windows::Forms::KeyEventHandler(this, &Form1::Form1_KeyDown);
			this->ResumeLayout(false);

		}
#pragma endregion
	private: System::Void Form1_Paint(System::Object^  sender, System::Windows::Forms::PaintEventArgs^  e) {
				Graphics^ g = e->Graphics;
				g->Clear(Color::White);				

				Pen^ blackPen = gcnew Pen(Color::Black);

				for (int index = 0; index < lines.Count; index++)
				{
					vector A;
					vector B;
					convertPointToVector(lines[index].start, A);
					convertPointToVector(lines[index].end, B);
					
					vector A1;
					vector B1;
					multiplyMatrixOnVector(resultState, A, A1);
					multiplyMatrixOnVector(resultState, B, B1);

					point a, b;
					convertVectorToPoint(A1, a);
					convertVectorToPoint(B1, b);
					g->DrawLine(blackPen, a.x, a.y, b.x, b.y);
				}
			 }

	private: System::Void Form1_Load(System::Object^  sender, System::EventArgs^  e) {
				 lines.Clear();
				 unit (resultState);
			 }

	private: System::Void openFileDialog1_FileOk(System::Object^  sender, System::ComponentModel::CancelEventArgs^  e) {

			 }

	private: System::Void btnOpen_Click(System::Object^  sender, System::EventArgs^  e) {
				 if (this->openFileDialog->ShowDialog() == System::Windows::Forms::DialogResult::OK)
				 {
					wchar_t fileName[1024];
					for (int index = 0; index < openFileDialog->FileName->Length; index++)
						fileName[index] = openFileDialog->FileName[index];
					fileName[openFileDialog->FileName->Length] = '\0';

					std::ifstream in;
					in.open(fileName);
					if (in.is_open()) {
						lines.Clear();
						unit(resultState);
						std::string str;
						getline (in, str);
						while (in) {
							if ((str.find_first_not_of(" \t\r\n") != std::string::npos) && (str[0] != '#')) {
								std::stringstream s(str);
								line item;
								s >> item.start.x >> item.start.y >> item.end.x >> item.end.y;
								std::string linename;
								s >> linename;
								item.name = gcnew String(linename.c_str());
								lines.Add(item);
							}
							getline (in, str);
						}
					}
					this->Refresh();
				 }
			 }
	private: System::Void Form1_KeyDown(System::Object^  sender, System::Windows::Forms::KeyEventArgs^  e) {

				 matrix currentState;
				 matrix variableState;
				 Rectangle rect = Form::ClientRectangle;
				 
				 switch(e->KeyCode) {
					case Keys::W :   move( 0, -1, currentState);	break;
					case Keys::S :   move( 0,  1, currentState);	break;
					case Keys::A :   move(-1,  0, currentState);	break;
					case Keys::D :   move( 1,  0, currentState);   break;	

					case Keys::T :   move(  0, -10, currentState); break;
					case Keys::G :   move(  0,  10, currentState); break;
					case Keys::F :   move(-10,   0, currentState); break;
					case Keys::H :   move( 10,   0, currentState); break;	

					case Keys::E : rotate( 0.05, currentState);    break;
					case Keys::Q : rotate(-0.05, currentState);    break;
						
					case Keys::X :  scale(    1.1, currentState);  break;
					case Keys::Z :  scale(1 / 1.1, currentState);  break;

					case Keys::U : reflectByHorizontal(currentState,  rect.Width); break;
					case Keys::J :   reflectByVertical(currentState, rect.Height); break;

					
					/*
					case Keys::R : break;					
					case Keys::Y : break;
					
					case Keys::I : break;
					case Keys::O : break;
					case Keys::K : break;
					case Keys::L : break;
					*/

					case Keys::Escape : unit(currentState); unit(resultState); break;
					default: unit(currentState);
				 }

				 multiplyOfMatrix(currentState, resultState, variableState);
				 set(variableState, resultState);
				 this->Refresh();
			 }
	};
}

