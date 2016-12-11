using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading;
using System.Threading.Tasks;
using System.Windows.Forms;
using System.Drawing;

namespace WhatAGame 
{
    class StartUpController : FormController
    {
        private Form form;

        public StartUpController(Form form)
        {
            this.form = form;
        }

        public async void DoAction(FormCallback callback)
        {
            foreach (var labelText in StartModel.DYNAMIC_STARTUP)
            {
                Label label = CreateRandomPositionLabel(labelText);
                form.Controls.Add(label);
                await Animate();
                form.Controls.Clear();
            }

            Button button = CreateWelcomeButton(callback, StartModel.WELCOME_BUTTON);
            form.Controls.Add(button);
        }

        private Label CreateRandomPositionLabel(string text)
        {
            Label label = new Label();
            label.Font = new Font("Verdana", 21, FontStyle.Regular); 
            label.TextAlign = ContentAlignment.MiddleCenter;
            label.Text = text;          
            
            label.AutoSize = true;

            label.Top  = 50 + new Random().Next() % 400;
            label.Left = 50 + new Random().Next() % 350;

            return label;        
        }

        private Button CreateWelcomeButton(FormCallback callback, string text)
        {
            Button button = new Button();
            button.Font = new Font("Verdana", 20, FontStyle.Regular);
            button.Text = text;

            button.ForeColor = Color.FromArgb(65, 50, 50, 50);

            button.Height = 100;
            button.Width = 250;

            button.TabStop = false;
            button.FlatStyle = FlatStyle.Flat;
            button.FlatAppearance.BorderSize = 0;

            button.Left = form.ClientSize.Width / 2 - button.Size.Width / 2;
            button.Top = form.ClientSize.Height / 2 - button.Size.Height / 2;

            button.Click += delegate(object sender, EventArgs e)
                            {
                                form.Controls.Clear();
                                callback();
                            };

            return button;
        }

        private async Task Animate()
        {
            for (int index = 240; index >= 2; index -= 2)
            {
                foreach (Control control in form.Controls)
                {
                    control.ForeColor = Color.FromArgb(index + 15, index, index, index);
                }
                await Task.Delay(1);
            }

            await Task.Delay(50);

            for (int index = 0; index <= 240; index += 2)
            {
                foreach (Control control in form.Controls)
                {
                    control.ForeColor = Color.FromArgb(index + 15, index, index, index);
                }
                await Task.Delay(1);
            }

            return;
        }
    }
}
