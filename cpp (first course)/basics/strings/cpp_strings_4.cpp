#include <string>
#include <vector>
#include <iostream>

using namespace std;

bool aloneStar(vector<vector <char>> a)
{ 
  for (int i = 0; i < (int)a.size(); i++)
     for (int j = 0; j < (int)a[0].size(); j++)
           if (a[i][j] == '*')
               return true;   
 
          return false;
}

vector <vector<char>> rotate(vector <vector<char>> v)
{
  vector <vector <char>> mas(v[0].size(), vector<char>(v.size()));
  
    for (int i = 0; i < (int)v.size(); i++)
      for (int j = 0; j < (int)v[0].size(); j++)
           mas[j][(int)v.size() - i - 1] = v[i][j];
 
        return mas;
}

vector <vector <char>> boundBox(vector <vector<char>> a)
{
   int L = (int)a.size(), U = (int)a[0].size(),
       D = 0,             R = 0;
        for (int i = 0; i < (int)a.size(); i++)
            for (int j = 0; j <(int)a[0].size(); j++)
                   if (a[i][j] == '*')
                        {       if (j < L) L = j;
                                if (j > R) R = j;
                                if (i > D) D = i;
                                if (i < U) U = i;
                        }  
vector <vector <char>> mas(D - U + 1, vector <char>(R - L + 1));
        int flagX = 0, flagY = 0;        
        for (int i = U; i <= D; i++)
        {       flagY = 0;
                for (int j = L; j <= R; j++)
                { mas[flagX][flagY] = a[i][j]; flagY++; }
                flagX++;
        }
  return mas;
}

bool IsCopy(vector <vector<char>> v, vector <vector<char>> b)
{  
        if (!(aloneStar(v) && aloneStar(b)))   
          return false;
       
        v = boundBox(v);
        b = boundBox(b);

        for (int i = 0; i < 4; i++)
                if (v == b)
                        return true;
                else
                        v = rotate(v);
       
  return false;
}

int main()
{      
        int n, m;
        cin >> n >> m;
        vector <vector <char>> v(n, vector <char>(m)); 
        for (int i = 0; i < n; i++)
               for (int j = 0; j < m; j++)
                      cin >> v[i][j];
        int k, l;
        cin >> k >> l;
        vector <vector<char>> b(k, vector <char>(l)); 
        for (int i = 0; i < k; i++)
                for (int j = 0; j < l; j++)
                        cin >> b[i][j];               
        if (IsCopy(v, b))
                cout << "YES";
        else
                cout << "NO";
  return 0;
}
