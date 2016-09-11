using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.IO;
using System.Windows.Forms;
using System.Runtime.Serialization.Formatters.Binary;

namespace SerializationWF
{
    public partial class Form1 : Form
    {
        private AbstractSerializer byteSerializer = new ByteSerializer();
        private AbstractSerializer xmlSerializer = new XMLSerializer();

        private Animal animal = null;

        public Form1()
        {
            InitializeComponent();
        }

        private void Form1_Load_1(object sender, EventArgs e)
        {

        }

        private void button2_Click_1(object sender, EventArgs e)
        {
            // xml serialize
            if (animal != null)
                xmlSerializer.Serialize(animal);
        }

        private void button3_Click_1(object sender, EventArgs e)
        {
            // xml deserialize
            animal = (Animal)xmlSerializer.Deserialize();
            AddOnSecondDataGridView(animal);
        }

        private void button4_Click_1(object sender, EventArgs e)
        {
            // bytes serialize
            if (animal != null)
                byteSerializer.Serialize(animal);
        }

        private void button5_Click_1(object sender, EventArgs e)
        {
            // bytes deserialize
            animal = (Animal)byteSerializer.Deserialize();
            AddOnSecondDataGridView(animal);
        }

        private void button1_Click_1(object sender, EventArgs e)
        {
            String type = list.Text;
            String name  = (!textBox1.Text.Equals("")) ? textBox1.Text : "Animal";
            Int32 height = (!textBox2.Text.Equals("")) ? Int32.Parse(textBox2.Text) : 0;
            Int32 weight = (!textBox3.Text.Equals("")) ? Int32.Parse(textBox3.Text) : 0;


            if (list.Text.Equals("Cat"))
            {
                animal = new Cat(name, height, weight);
            }
            else if (list.Text.Equals("Dog"))
            {
                animal = new Dog(name, height, weight);
            }

            if (animal != null)
            {
                AddOnFirstDataGridView(animal);
            }

            list.ClearSelected();
            textBox1.Clear();
            textBox2.Clear();
            textBox3.Clear();
        }

        private void dataGridView1_CellContentClick_1(object sender, DataGridViewCellEventArgs e)
        {
            // data grid view first
        }

        private void dataGridView2_CellContentClick(object sender, DataGridViewCellEventArgs e)
        {
            // data grid view second
        }

        private void AddOnFirstDataGridView(Animal animal)
        {
            if (animal != null)
            {
                dataGridView1.Rows.Add(animal.GetTypeName(), animal.name, animal.height.ToString(), animal.weight.ToString());
            }
        }

        private void AddOnSecondDataGridView(Animal animal)
        {
            if (animal != null)
            {
                dataGridView2.Rows.Add(animal.GetTypeName(), animal.name, animal.height.ToString(), animal.weight.ToString());
            }
        }

        private void button6_Click(object sender, EventArgs e)
        {
            list.ClearSelected();
            textBox1.Clear();
            textBox2.Clear();
            textBox3.Clear();
            dataGridView1.Rows.Clear();
            dataGridView2.Rows.Clear();
        }
    }
}
