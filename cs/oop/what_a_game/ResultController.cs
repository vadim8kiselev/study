using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using System.Drawing;

namespace WhatAGame
{
    class ResultController : FormController
    {
        private Form form;

        private List<int> result = ResultHolder.GetResult();

        public ResultController(Form form)
        {
            this.form = form;
        }

        public async void DoAction(FormCallback callback)
        {
            int winner = result.IndexOf(result.Max());
            Label label = createLabel(ResultModel.RESULTS[winner]);
            form.Controls.Add(label);
            await Animate();

            Button restart = createButton(ResultModel.RESTART, delegate(object sender, EventArgs e)
                                                                {
                                                                    form.Controls.Clear();
                                                                    ResultHolder.RefreshResult();
                                                                    callback();
                                                                }
                                          );
            restart.Top = 450;
            restart.Left = 100;
            form.Controls.Add(restart);

            Button exit = createButton(ResultModel.EXIT, delegate(object sender, EventArgs e)
                                                        {
                                                            form.Controls.Clear();
                                                            ResultHolder.RefreshResult();
                                                            form.Close();
                                                        }
                                       );
            exit.Top = 450;
            exit.Left = 500;
            form.Controls.Add(exit);
        }

        private Label createLabel(string text)
        {
            Label label = new Label();
            label.Font = new Font("Verdana", 18, FontStyle.Regular);
            label.ForeColor = Color.FromArgb(255, 240, 240, 240);
            label.TextAlign = ContentAlignment.MiddleCenter;
            label.Text = text;

            label.Width = 900;
            label.Height = 400;

            label.Left = 50;

            return label;
        }

        private Button createButton(string text, EventHandler callback)
        {
            Button button = new Button();
            button.Font = new Font("Verdana", 14, FontStyle.Regular);
            button.Text = text;

            button.Width = 400;
            button.Height = 100;

            button.TabStop = false;
            button.FlatStyle = FlatStyle.Flat;
            button.FlatAppearance.BorderSize = 0;

            button.Click += callback;

            return button;
        }

        private async Task Animate()
        {
            for (int index = 240; index >= 4; index -= 4)
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
