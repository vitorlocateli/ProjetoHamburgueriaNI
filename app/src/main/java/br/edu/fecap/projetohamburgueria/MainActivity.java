package br.edu.fecap.projetohamburgueria;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;

public class MainActivity extends AppCompatActivity {

    private TextView menuTextView;
    private Button checkoutButton;
    private Switch switch1;
    private Switch switch2;
    private TextInputLayout inputLayout1;
    private TextInputLayout inputLayout2;
    private int numeroPedidoAtual = 1830;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inicialização das views
        menuTextView = findViewById(R.id.menuTextView);
        checkoutButton = findViewById(R.id.checkoutButton);
        switch1 = findViewById(R.id.switch1);
        switch2 = findViewById(R.id.switch2);
        inputLayout1 = findViewById(R.id.textInputLayout1);
        inputLayout2 = findViewById(R.id.textInputLayout2);

        // Configuração do clique do TextView para mostrar opções do menu
        menuTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showMenuOptionsDialog();
            }
        });

        // Configuração do clique do botão
        checkoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Geração do número do pedido
                int numeroPedido = numeroPedidoAtual++;

                // Obtenção dos estados dos switches
                boolean adicionarBatatas = switch1.isChecked();
                boolean adicionarBebida = switch2.isChecked();

                // Obtenção das instruções especiais
                String instrucoesEspeciais = inputLayout1.getEditText().getText().toString();

                // Obtenção do nome do cliente
                String nomeCliente = inputLayout2.getEditText().getText().toString();

                // Simulação do resumo do pedido
                String resumoPedido = "Resumo do Pedido\n"
                        + "Cliente: " + nomeCliente + "\n"
                        + "Instruções Especiais: " + instrucoesEspeciais + "\n"
                        + "Item Selecionado: " + menuTextView.getText().toString() + "\n"
                        + "Itens Adicionais:\n"
                        + (adicionarBatatas ? "- Batatas Fritas\n" : "")
                        + (adicionarBebida ? "- Bebida\n" : "");

                // Criação do AlertDialog para confirmar o pedido
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Confirmação do Pedido")
                        .setMessage(resumoPedido)
                        .setPositiveButton("OK", null)
                        .setCancelable(false) // Evitar que o diálogo seja fechado ao tocar fora dele
                        .show();
            }
        });
    }

    private void showMenuOptionsDialog() {
        final CharSequence[] items = {"Hambúrguer Tradicional", "Hambúrguer Duplo", "Hambúrguer Vegetariano"};

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Selecione uma opção do menu")
                .setItems(items, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        menuTextView.setText(items[which]);
                    }
                });
        builder.show();
    }
}
