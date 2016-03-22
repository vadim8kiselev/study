#pragma once
#include "stdafx.h"
#include "Transform.h"
#include "clip.h"
#include <algorithm>

using namespace std;

bool unclip(point &A, point &B, point Pmin, point Pmax) {
 
    if(A.x - B.x == 0 && B.y - A.y == 0)
    	return (A.x >= Pmin.x && A.x <= Pmax.x && A.y >= Pmin.y && A.y <= Pmax.y);
 
    if ((A.x - B.x) == 0 && (A.x - Pmin.x) * (Pmax.x - A.x) <= 0 || 
    	(A.y - B.y) == 0 && (A.y - Pmin.y) * (Pmax.y - A.y) <= 0)
			return true; 
	
    float points[4];

    points[0] = A.x - B.x;     
    points[1] = B.x - A.x;     
    points[2] = A.y - B.y;     
    points[3] = B.y - A.y;  

	
	float qu[4];

	qu[0] = A.x - Pmin.x;
	qu[1] = Pmax.x - A.x;
	qu[2] = A.y - Pmin.y;
	qu[3] = Pmax.y - A.y;
		
	float tmin = 0;
    float tmax = 1;

    for (int index = 0; index < 4; index++)
    {    	
		float temp = (qu[index] + 0.0) / points[index];
        if(points[index] < 0)
			tmin = max(tmin, temp);        
        else if(points[index] > 0)
			tmax = min (tmax, temp);        
    }

    if (tmin > tmax)
    	return true;	

    float delta_x = B.x - A.x;
    float delta_y = B.y - A.y;

    B.x = A.x + tmax * delta_x;
    A.x = A.x + tmin * delta_x;
    B.y = A.y + tmax * delta_y;
    A.y = A.y + tmin * delta_y;
}