using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace WhatAGame
{
    public delegate void FormCallback();

    interface FormController
    {
        void DoAction(FormCallback callback);
    }
}
