value struct point 
{
	float x;
	float y;
};

value struct line 
{
	point start;
	point end;
	System::String^ name;
};

#define RANK 3

typedef double vector[RANK];

typedef double matrix[RANK][RANK];


extern matrix resultState;


void multiplyOfMatrix(matrix source, matrix target, matrix result);

void multiplyMatrixOnVector(matrix source, vector target, vector result);

void set(matrix source, matrix target);

void convertPointToVector(point source, vector target);

void convertVectorToPoint(vector source, point &target);

void makeHomogenVec(double x, double y, vector target);

void unit(matrix target);

void move(double x, double y, matrix target);

void rotate(double phi, matrix target);

void reflectByHorizontal(matrix target, double width);

void reflectByVertical(matrix target, double height);

void scale(double size, matrix target);

void scaleByOneSide(double measure, matrix target, double width, double height, bool isHeight, matrix result, matrix tmp);

void scaleAroundCenter(double measure, matrix target, double width, double height, matrix result, matrix tmp);

void rotateAroundPoint(double phi, matrix target, double width, double height, matrix result, matrix tmp);

void scaleMatrixAroundPoint(double x, double y, matrix target);

void frame(float Vx, float Vy, float Vcx, float Vcy,
			float Wx, float Wy, float Wcx, float Wcy,
			matrix target, float width);