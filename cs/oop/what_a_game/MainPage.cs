using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace WhatAGame
{
    public partial class MainPage : Form
    {
        public MainPage()
        {
            InitializeComponent();
            StartPosition = FormStartPosition.CenterScreen;
            FormBorderStyle = FormBorderStyle.FixedSingle;
            Init();
        }

        public void Init()
        {
            Panel panel = new Panel();
            panel.Size = this.ClientSize;
            panel.MouseHover += delegate(object s, EventArgs e)
                                {
                                    this.Controls.Clear();
                                    new StartUpController(this).DoAction(delegate() { StartGame(); });
                                };
            this.Controls.Add(panel);
        }

        public void StartGame()
        {
            new GameController(this).DoAction(delegate() { ShowResult(); });
        }

        public void ShowResult()
        {
            new ResultController(this).DoAction(delegate() { StartGame(); });
        }
    }
}
