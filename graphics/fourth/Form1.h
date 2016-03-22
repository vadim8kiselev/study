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
	private: float left, right, top, bottom; 
	private: float Wcx, Wcy, Wx, Wy; 
	private: float Vcx, Vcy, Vx, Vy;
	private: bool drawing;

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
			this->ClientSize = System::Drawing::Size(580, 500);
			this->Controls->Add(this->btnOpen);
			this->KeyPreview = true;
			this->MinimumSize = System::Drawing::Size(500, 500);
			this->Name = L"Form1";
			this->Text = L"Form1";
			this->Load += gcnew System::EventHandler(this, &Form1::Form1_Load);
			this->Paint += gcnew System::Windows::Forms::PaintEventHandler(this, &Form1::Form1_Paint);
			this->KeyDown += gcnew System::Windows::Forms::KeyEventHandler(this, &Form1::Form1_KeyDown);
			this->Resize += gcnew System::EventHandler(this, &Form1::Form1_Resize);
			this->ResumeLayout(false);

		}
#pragma endregion
	private: System::Void Form1_Paint(System::Object^  sender, System::Windows::Forms::PaintEventArgs^  e) {
				Graphics^ g = e->Graphics;
				g->Clear(Color::White);				

				Pen^ blackPen = gcnew Pen(Color::Black);

				Pen^ rectPen  = gcnew Pen(Color::Red, 1);
				g->DrawRectangle(rectPen, Wcx, top, Wx, Wy);
				
				 System::Drawing::Font^ font = gcnew System::Drawing::Font("Arial", 10);
				 SolidBrush^ fontBrush = gcnew SolidBrush(Color::Red);

				point min, max;

				min.x = Wcx;
				min.y = Wcy - Wy;
				max.x = Wcx + Wx;
				max.y = Wcy;

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

					if (!unclip(a, b, min, max))
						g->DrawLine(blackPen, a.x, a.y, b.x, b.y);

					if (drawing) 
						g->DrawString(lines[index].name, font, fontBrush, (a.x + (b.x - a.x) / 2), (a.y + (b.y - a.y) / 2)); 
					
				}
			 }

	private: System::Void TurnCoordinates() {
				 Rectangle rect = Form::ClientRectangle;
				 unit(resultState); 

				 matrix currentState, tmp1, tmp2; 
				 move(0, -rect.Height, currentState); 
				 multiplyOfMatrix(currentState, resultState, tmp1); 

				 unit(currentState);
				 currentState[1][1] = -1; 

				 multiplyOfMatrix(currentState, tmp1, tmp2); 
				 set(tmp2, resultState);
			 }

	private: System::Void Form1_Load(System::Object^  sender, System::EventArgs^  e) {
				 lines.Clear();
				 unit (resultState);

				 left = 20;
				 right = 20; 
				 top = 70; 
				 bottom = 20;

				 Wcx = left; 
				 Wcy = Form::ClientRectangle.Height - bottom; 
				 Wx = Form::ClientRectangle.Width - left - right; 
				 Wy = Form::ClientRectangle.Height - top - bottom;

				 drawing = false;
			 }
			 
	private: System::Void Form1_Resize(System::Object^  sender, System::EventArgs^  e) {

				 float oldWx = Wx, oldWy = Wy;
				 matrix currentState;
				 matrix variableState;

				 Wcx = left; 
				 Wcy = Form::ClientRectangle.Height - bottom; 
				 Wx = Form::ClientRectangle.Width - left - right; 
				 Wy = Form::ClientRectangle.Height - top - bottom;

				 scaleMatrixAroundPoint(Wx/oldWx, Wy/oldWy, currentState); 
				 multiplyOfMatrix(currentState, resultState, variableState); 
				 set(variableState, resultState); 

				 this->Refresh();
			 }

	private: System::Void openFileDialog1_FileOk(System::Object^  sender, System::ComponentModel::CancelEventArgs^  e) {

			 }

	private: System::Void btnOpen_Click(System::Object^  sender, System::EventArgs^  e) {
				 
				 Rectangle rect = Form::ClientRectangle;

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
						if (in) {
								 if ((str.find_first_not_of(" \t\r\n") != std::string::npos)  && (str[0] != '#')) {
										 std::stringstream s(str);
										 float q, w, e, r;
										 s >> q >> w >> e >> r;
										 Vcx = q;
										 Vcy = w;
										 Vx = e;
										 Vy = r;
								 }
								 getline(in, str);
						}
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
					frame(Vx, Vy, Vcx, Vcy, Wx, Wy, Wcx, Wcy, resultState, rect.Width);
				    TurnCoordinates();
					this->Refresh();
				 }
			 }
	private: System::Void Form1_KeyDown(System::Object^  sender, System::Windows::Forms::KeyEventArgs^  e) {

				 matrix currentState;
				 matrix variableState;
				 Rectangle rect = Form::ClientRectangle;
				 
				 switch(e->KeyCode) {
					case Keys::W :   move( 0, -1, currentState);   break;
					case Keys::S :   move( 0,  1, currentState);   break;
					case Keys::A :   move(-1,  0, currentState);   break;
					case Keys::D :   move( 1,  0, currentState);   break;	

					case Keys::T :   move(  0, -10, currentState); break;
					case Keys::G :   move(  0,  10, currentState); break;
					case Keys::F :   move(-10,   0, currentState); break;
					case Keys::H :   move( 10,   0, currentState); break;	

					case Keys::E : rotate( 0.05, currentState);    break;
					case Keys::Q : rotate(-0.05, currentState);    break;
						
					case Keys::X :  scale(    1.1, currentState);  break;
					case Keys::Z :  scale(1 / 1.1, currentState);  break;

					case Keys::U :   reflectByHorizontal(currentState,  rect.Width); break;
					case Keys::J :     reflectByVertical(currentState, rect.Height); break;

					case Keys::C : scaleAroundCenter(1.1 / 1, currentState, rect.Width, rect.Height, resultState, variableState); break;
                    case Keys::V : scaleAroundCenter(1 / 1.1, currentState, rect.Width, rect.Height, resultState, variableState); break;

					case Keys::R : rotateAroundPoint( 0.05, currentState, rect.Width, rect.Height, resultState, variableState); break;					
					case Keys::Y : rotateAroundPoint(-0.05, currentState, rect.Width, rect.Height, resultState, variableState); break;
					
					case Keys::I : scaleByOneSide(1.1 / 1, currentState, rect.Width, rect.Height, false, resultState, variableState); break;
                    case Keys::K : scaleByOneSide(1 / 1.1, currentState, rect.Width, rect.Height, false, resultState, variableState); break;

                    case Keys::O :  scaleByOneSide(1.1 / 1, currentState, rect.Width, rect.Height, true, resultState, variableState); break;
                    case Keys::L :  scaleByOneSide(1 / 1.1, currentState, rect.Width, rect.Height, true, resultState, variableState); break;

					case Keys::Escape : unit(currentState); unit(resultState); TurnCoordinates(); break;

					case Keys::P : drawing = !drawing; unit(currentState); break;
					default: unit(currentState);
				 }

				 multiplyOfMatrix(currentState, resultState, variableState);
				 set(variableState, resultState);
				 this->Refresh();
			 }
};
}