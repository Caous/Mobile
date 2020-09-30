package com.example.padarialucroeperda

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.core.text.set
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.Exception
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var listaProdutos = ArrayList<StringTokenizer>();
        val sh = getSharedPreferences("listadeprodutos", Context.MODE_PRIVATE)

        btnDespesaLucro.setOnClickListener { x: View? ->
            var validado = true;
            var valVenda = valorVenda.text.toString().toDouble();
            var valCompra = valorCompra.text.toString().toDouble();

            if (valCompra <= 0.0) {
                Toast.makeText(
                    this,
                    "Por favor informar valores positivos na compra",
                    Toast.LENGTH_SHORT
                ).show();
                var validado = false;
            }
            if (valVenda <= 0.0) {
                Toast.makeText(
                    this,
                    "Por favor informar valores positivos na venda",
                    Toast.LENGTH_SHORT
                ).show();
                var validado = false;
            }
            if (validado) {
                var rsl = "";
                if ((valVenda - valCompra) <= 0.0) {
                    rsl = "Você teve um prejuízo de R$ " + (valVenda - valCompra);
                } else {
                    rsl = "Você teve lucro de R$ " + (valVenda - valCompra);
                }
                txtLucrosDespesas.setText(rsl);

            }
        }

        btnCadastrar.setOnClickListener { x: View? ->

            var validado = true;
            var valVenda = valorVenda.text.toString().toDouble();
            var valCompra = valorCompra.text.toString().toDouble();

            if (valCompra <= 0.0) {
                Toast.makeText(
                    this,
                    "Por favor informar valores positivos na compra",
                    Toast.LENGTH_SHORT
                ).show();
                var validado = false;
            }
            if (valVenda <= 0.0) {
                Toast.makeText(
                    this,
                    "Por favor informar valores positivos na venda",
                    Toast.LENGTH_SHORT
                ).show();
                var validado = false;
            }
            val produtoDesc = nomeProduto.text.toString();
            if (produtoDesc.isNullOrEmpty()) {
                Toast.makeText(this, "Por favor informar nome do produto", Toast.LENGTH_SHORT)
                    .show();
                var validado = false;
            }

            if (validado) {
                Toast.makeText(this, "Adicionado com sucesso", Toast.LENGTH_SHORT).show();
                sh.edit()
                    .putString(produtoDesc.toString(), valCompra.toString() +";"+ valVenda.toString())
                    .apply()
                nomeProduto.setText("");
                valorCompra.setText("");
                valorVenda.setText("");
            }

        }

        btnAbrir.setOnClickListener { x: View? ->

            var validado = true;
            val produtoDesc = nomeProduto.text.toString();
            if (produtoDesc.isNullOrEmpty()) {
                Toast.makeText(this, "Por favor informar nome do produto", Toast.LENGTH_SHORT)
                    .show();
                var validado = false;
            }
            if (validado) {
                var count = 0;

                nomeProduto.setText(produtoDesc)
                var texto = sh.getString(produtoDesc, "")
                var valorCompras = texto.toString().split(";");
                valorCompra.setText(valorCompras[0]);
                valorVenda.setText(valorCompras[1]);


            }

        }
    }
}