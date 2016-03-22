#pragma once
#include "stdafx.h"
#include "Transform.h"
#include <math.h>

matrix resultState;

void multiplyOfMatrix(matrix source, matrix target, matrix result) 
{
	for(int index = 0; index < RANK; index++) 
	{
		for(int jndex = 0; jndex < RANK; jndex++) 
		{
			double value = 0;

			for(int kndex = 0; kndex < RANK; kndex++)
				value += source[index][kndex] * target[kndex][jndex];

			result[index][jndex] = value;
		}
	}
}

void multiplyMatrixOnVector(matrix source, vector target, vector result) 
{
	for(int index = 0; index < RANK; index++) 
	{
		double value = 0;
		for(int jndex = 0; jndex < RANK; jndex++) 
		{
			value += source[index][jndex] * target[jndex];
		}
		result[index] = value;
	}
}

void set(matrix a, matrix b) 
{
	for(int index = 0; index < RANK; index++) 
	{	
		for(int jndex = 0; jndex < RANK; jndex++) 
		{
			b[index][jndex] = a[index][jndex];
		}
	}
}

void convertPointToVector(point source, vector target) 
{
	target[0] = source.x; 
	target[1] = source.y;
	target[2] = 1;
}

void convertVectorToPoint(vector source, point &target) 
{
	target.x = float(((double) source[0]) / source[2]);
	target.y = float(((double) source[1]) / source[2]);
}

void makeHomogenVec(double x, double y, vector target)
{
	target[0] = x; 
	target[1] = y; 
	target[2] = 1;
}

void unit(matrix target) 
{
	for(int index = 0; index < RANK; index++) 
	{	
		for(int jndex = 0; jndex < RANK; jndex++)  
		{
			if (index == jndex)
			{
				target[index][jndex] = 1;
			}
			else
			{
				target[index][jndex] = 0;
			}
		}
	}
}

void move(double x, double y, matrix target) 
{
	unit (target);
	target[0][RANK-1] = x;
	target[1][RANK-1] = y;
}

void rotate(double phi, matrix target) 
{
	unit (target);
	target[0][0] = cos(phi); 
	target[0][1] = -sin(phi);
	target[1][0] = sin(phi); 
	target[1][1] = cos(phi);
}

void scale(double size, matrix target) 
{
	unit (target);
	target[0][0] = size; 
	target[1][1] = size;
}

void reflectByHorizontal(matrix target, double width)
{
	unit(target);
	target[0][0] = -1;
	target[0][RANK - 1] = width;
}

void reflectByVertical(matrix target, double height)
{
	unit(target);
	target[1][1] = -1;
	target[1][RANK - 1] = height;
}

void scaleByOneSide(double measure, matrix target, double width, double height, bool isHeight, matrix result, matrix tmp)
{
	move(-width / 2, -height / 2, target); 
    multiplyOfMatrix(target, result, tmp);
    set(tmp, result);
	unit(target);
 
	if (isHeight)
	{
		target[1][1] = measure;	
	} else
	{
		target[0][0] = measure;
	} 
    multiplyOfMatrix(target, result, tmp);
    set(tmp, result);
    move(width / 2, height / 2, target);
}

void scaleAroundCenter(double measure, matrix target, double width, double height, matrix result, matrix tmp)
{
	move(-width / 2, -height / 2, target); 
	multiplyOfMatrix(target, result, tmp);
	set(tmp, result); 
		scale(measure, target); 
	multiplyOfMatrix(target, result, tmp);
	set(tmp, result); 
	move(width / 2, height / 2, target);
}

void rotateAroundPoint(double phi, matrix target, double width, double height, matrix result, matrix tmp)
{
	move(-width / 2, -height / 2, target); 
    multiplyOfMatrix(target, result, tmp);
    set(tmp, result); 
		rotate(phi, target); 
    multiplyOfMatrix(target, result, tmp);
	set(tmp, result); 
    move(width / 2, height / 2, target);
}

void scaleMatrixAroundPoint(double x, double y, matrix target)
{
	unit (target);
	target[0][0] = x; 
	target[1][1] = y;
}

void frame (float Vx, float Vy, float Vcx, float Vcy,
			float Wx, float Wy, float Wcx, float Wcy,
			matrix target, float width) {
	unit (target);
	matrix R, T1;
	move(-Vcx, -Vcy, R);
	multiplyOfMatrix(R, target, T1);
	set(T1, target);
	scaleMatrixAroundPoint(Wx/Vx, Wy/Vy, R);
	multiplyOfMatrix(R, target, T1);
	set(T1, target);
	reflectByHorizontal(R, width);
	multiplyOfMatrix(R, target, T1);
	set(T1, target);
	move(Wcx, Wcy, R);
	multiplyOfMatrix(R, target, T1);
	set(T1, target);
}