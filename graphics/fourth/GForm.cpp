#include "stdafx.h"
#include <fstream>
#include <sstream>
#include <iostream>
#include "Transform.h"
#include "clip.h"
#include "Form1.h"

using namespace GForm;

[STAThreadAttribute]
int main(array<System::String ^> ^args)
{
	Application::EnableVisualStyles();
	Application::SetCompatibleTextRenderingDefault(false); 

	Application::Run(gcnew Form1());
	return 0;
}