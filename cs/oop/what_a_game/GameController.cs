using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using System.Drawing;

namespace WhatAGame
{
    class GameController : FormController
    {
        private Form form;

        private List<KeyValuePair<Label, List<Button>>> pages = new List<KeyValuePair<Label, List<Button>>>();

        private List<int> result = Enumerable.Repeat(0, 5).ToList();

        public GameController(Form form)
        {
            this.form = form;
        }

        public async void DoAction(FormCallback callback)
        {
            foreach (var entry in new GameModel().GAME) // wtf c# :(
            {
                Label question = CreateQuestion(entry.Key);

                List<Button> answers = new List<Button>();
                foreach (var innerEntry in entry.Value)
                {
                    Button button = CreateAnswer(innerEntry.Key);
                    button.Click += async delegate(object sender, EventArgs e)
                                    {
                                        form.Controls.Clear();
                                        foreach (var target in innerEntry.Value)
                                        {
                                            result[target - 1]++;
                                        }
                                        await ShowPage(callback);
                                    };
                    answers.Add(button);
                }
                pages.Add(new KeyValuePair<Label, List<Button>>(question, answers));
            }

            await ShowPage(callback);
        }

        private async Task ShowPage(FormCallback callback)
        {
            if (pages.Count == 0)
            {
                form.Controls.Clear();
                ResultHolder.SetResult(result);
                callback();
                return;
            }

            List<Button> answers = pages[pages.Count - 1].Value;
            switch(answers.Count)
            {
                case 2:
                    answers[0].Top = 350;
                    answers[0].Left = 50;

                    answers[1].Top = 350;
                    answers[1].Left = 550;                   
                    break;
                case 3:
                    answers[0].Top = 300;
                    answers[0].Left = 50;

                    answers[1].Top = 300;
                    answers[1].Left = 550;

                    answers[2].Top = 400;
                    answers[2].Left = 300;
                    break;
                case 4:
                    answers[0].Top = 300;
                    answers[0].Left = 50;

                    answers[1].Top = 300;
                    answers[1].Left = 550;

                    answers[2].Top = 400;
                    answers[2].Left = 50;

                    answers[3].Top = 400;
                    answers[3].Left = 550;
                    break;
                case 5:
                    answers[0].Top = 250;
                    answers[0].Left = 50;

                    answers[1].Top = 250;
                    answers[1].Left = 550;

                    answers[2].Top = 350;
                    answers[2].Left = 50;

                    answers[3].Top = 350;
                    answers[3].Left = 550;

                    answers[4].Top = 450;
                    answers[4].Left = 300;
                    break;
            }

            Label question = pages[pages.Count - 1].Key;
            form.Controls.Add(question);

            foreach (Button answer in answers)
            {
                form.Controls.Add(answer);
            }
            await Animate();
            pages.RemoveAt(pages.Count - 1);            
        }

        private async Task Animate()
        {
            for (int index = 240; index >= 10; index -= 10)
            {
                foreach (Control control in form.Controls)
                {
                    control.ForeColor = Color.FromArgb(index + 15, index, index, index);
                }
                await Task.Delay(1);
            }
            return;
        }

        private Label CreateQuestion(string question)
        {
            Label label = new Label();
            label.Font = new Font("Tahoma", 20, FontStyle.Regular);
            label.ForeColor = Color.FromArgb(255, 240, 240, 240);
            label.TextAlign = ContentAlignment.MiddleCenter;
            label.Text = question;

            label.Width = form.ClientSize.Width;
            label.Height = 250;

            return label; 
        }

        private Button CreateAnswer(string answer)
        {
            Button button = new Button();
            button.Font = new Font("Verdana", 14, FontStyle.Regular);
            button.ForeColor = Color.FromArgb(255, 240, 240, 240);
            button.Text = answer;

            button.Width = 400;
            button.Height = 100;

            button.TabStop = false;
            button.FlatStyle = FlatStyle.Flat;
            button.FlatAppearance.BorderSize = 0;

            return button;
        }
    }
}
