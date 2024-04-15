package com.example.androidprojectcollection;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class CalculatorActivity extends AppCompatActivity {

    private TextView inputDisplay, outputDisplay;

//    private DecimalFormat decimalFormat;
    private Button button0, button1, button2, button3, button4, button5, button6, button7, button8, button9,
            buttonDot, buttonAdd, buttonSub, buttonMultiply, buttonDivide, buttonClear, buttonEqual;

    int ref_lastNum = 0;
    boolean isChanged = false;
    Stack<String> calcu = new Stack<>();

    CalculatorMagic calcuObject = new CalculatorMagic();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);

        outputDisplay = (TextView) findViewById(R.id.output);

        buttonDot = (Button) findViewById(R.id.BtnDot);
        button0 = (Button) findViewById(R.id.Btn0);
        button1 = (Button) findViewById(R.id.Btn1);
        button2 = (Button) findViewById(R.id.Btn2);
        button3 = (Button) findViewById(R.id.Btn3);
        button4 = (Button) findViewById(R.id.Btn4);
        button5 = (Button) findViewById(R.id.Btn5);
        button6 = (Button) findViewById(R.id.Btn6);
        button7 = (Button) findViewById(R.id.Btn7);
        button8 = (Button) findViewById(R.id.Btn8);
        button9 = (Button) findViewById(R.id.Btn9);
        buttonAdd = (Button) findViewById(R.id.BtnPlus);
        buttonSub = (Button) findViewById(R.id.BtnMinus);
        buttonDivide = (Button) findViewById(R.id.BtnDivide);
        buttonMultiply = (Button) findViewById(R.id.BtnMultiply);
        buttonClear = (Button) findViewById(R.id.BtnReset);
        buttonEqual = (Button) findViewById(R.id.BtnEquals);

        List<Button> buttons = new ArrayList<>();
        buttons.add(button0);
        buttons.add(button1);
        buttons.add(button2);
        buttons.add(button3);
        buttons.add(button4);
        buttons.add(button5);
        buttons.add(button6);
        buttons.add(button7);
        buttons.add(button8);
        buttons.add(button9);

        for (Button b: buttons) {
            b.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Button click = (Button) view;
                    String digit = click.getText().toString();

                    if (outputDisplay.getText().equals("0")) {
                        outputDisplay.setText(digit);
                    } else {
                        outputDisplay.setText(outputDisplay.getText() + digit);
                    }

                    if (isChanged || calcu.isEmpty()) {
                        calcu.push(outputDisplay.getText().toString().substring(ref_lastNum, outputDisplay.length()));
                        isChanged = false;
                    } else {
                        calcu.pop();
                        calcu.push(outputDisplay.getText().toString().substring(ref_lastNum, outputDisplay.length()));
                    }

                    String res = calcuObject.forSequence(((Stack<String>)calcu.clone()));


                    if (calcuObject.isFloat(res)) {
                        inputDisplay.setText("= " + Float.parseFloat(res));
                    } else {
                        inputDisplay.setText("= " + ((int) Float.parseFloat(res)));
                    }
                }
            });
        }

        buttonDot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Button x = (Button) view;
                String p = x.getText().toString();

                if (!outputDisplay.getText().toString().substring(ref_lastNum, outputDisplay.length()).contains(".")){
                    outputDisplay.setText(outputDisplay.getText() + p);
                }
            }
        });

        List<Button> operators = new ArrayList<>();
        operators.add(buttonAdd);
        operators.add(buttonSub);
        operators.add(buttonMultiply);
        operators.add(buttonDivide);

        inputDisplay = (TextView) findViewById(R.id.input);

        for (Button b : operators) {
            b.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Button o = (Button) view;
                    String operator = o.getText().toString();

                    System.out.println(calcu.isEmpty());
                    switch(operator) {
                        case "+":
                            if (calcu.size() % 2 != 0) {
                                if (!calcu.peek().equals("+") && !isChanged) {
                                    calcu.push("+");
                                    outputDisplay.setText(outputDisplay.getText() + "+");
                                } else {
                                    outputDisplay.setText(outputDisplay.getText().toString().substring(0, outputDisplay.length() - 1) + "+");
                                    calcu.pop();
                                    calcu.push("+");
                                }

                                ref_lastNum = outputDisplay.length();
                                isChanged = true;
                            }
                            break;
                        case "-":
                            if (calcu.size() % 2 != 0) {
                                if (!calcu.peek().equals("-") && !isChanged) {
                                    calcu.push("-");
                                    outputDisplay.setText(outputDisplay.getText() + "-");
                                } else {
                                    outputDisplay.setText(outputDisplay.getText().toString().substring(0, outputDisplay.length() - 1) + "-");
                                    calcu.pop();
                                    calcu.push("-");
                                }

                                ref_lastNum = outputDisplay.length();
                                isChanged = true;
                            }
                            break;
                        case "*":
                            if (calcu.size() % 2 != 0) {
                                if (!calcu.peek().equals("*") && !isChanged) {
                                    calcu.push("*");
                                    outputDisplay.setText(outputDisplay.getText() + "*");
                                } else {
                                    outputDisplay.setText(outputDisplay.getText().toString().substring(0, outputDisplay.length() - 1) + "*");
                                    calcu.pop();
                                    calcu.push("*");
                                }

                                ref_lastNum = outputDisplay.length();
                                isChanged = true;
                            }
                            break;
                        case "/":
                            if (calcu.size() % 2 != 0) {
                                if (!calcu.peek().equals("/") && !isChanged) {
                                    calcu.push("/");
                                    outputDisplay.setText(outputDisplay.getText() + "/");
                                } else {
                                    outputDisplay.setText(outputDisplay.getText().toString().substring(0, outputDisplay.length() - 1) + "/");
                                    calcu.pop();
                                    calcu.push("/");
                                }

                                ref_lastNum = outputDisplay.length();
                                isChanged = true;
                            }
                            break;
                    }

                }
            });
        }

        buttonEqual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (calcu.size() == 0) {
                    return;
                }
                String res = calcuObject.forMDAS(((Stack<String>)calcu.clone()));

                if (calcuObject.isFloat(res)) {
                    inputDisplay.setText("= " + Float.parseFloat(res));
                } else {
                    inputDisplay.setText("= " + ((int) Float.parseFloat(res)));
                }

                outputDisplay.setText("");
                calcu = new Stack<>();
                isChanged = true;
                ref_lastNum = 0;
            }
        });

        buttonClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                outputDisplay.setText("");
                inputDisplay.setText("");
                calcu = new Stack<>();
                ref_lastNum = 0;
                isChanged = true;
            }
        });
    }


//        button0.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                inputDisplay.setText(inputDisplay.getText() + "0");
//                calc.add('0');
//            }
//
//        });
//        button1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                inputDisplay.setText(inputDisplay.getText() + "1");
//                calc.add('1');
//            }
//        });
//        button2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                inputDisplay.setText(inputDisplay.getText() + "2");
//                calc.add('2');
//            }
//        });
//        button3.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                inputDisplay.setText(inputDisplay.getText() + "3");
//                calc.add('3');
//            }
//        });
//        button4.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                inputDisplay.setText(inputDisplay.getText() + "4");
//                calc.add('4');
//            }
//        });
//        button5.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                inputDisplay.setText(inputDisplay.getText() + "5");
//                calc.add('5');
//            }
//        });
//        button6.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                inputDisplay.setText(inputDisplay.getText() + "6");
//                calc.add('6');
//            }
//        });
//        button7.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                inputDisplay.setText(inputDisplay.getText() + "7");
//                calc.add('7');
//            }
//        });
//        button8.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                inputDisplay.setText(inputDisplay.getText() + "8");
//                calc.add('8');
//            }
//        });
//        button9.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                inputDisplay.setText(inputDisplay.getText() + "9");
//                calc.add('9');
//            }
//        });
//        buttonAdd.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                inputDisplay.setText(inputDisplay.getText() + "+");
//                calc.add('+');
//            }
//        });
//        buttonSub.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                inputDisplay.setText(inputDisplay.getText() + "-");
//                calc.add('-');
//            }
//        });
//        buttonMultiply.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                inputDisplay.setText(inputDisplay.getText() + "*");
//                calc.add('*');
//            }
//        });
//        buttonDivide.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                inputDisplay.setText(inputDisplay.getText() + "/");
//                calc.add('/');
//            }
//        });
//        buttonDot.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                inputDisplay.setText(inputDisplay.getText() + ".");
//                calc.add('.');
//            }
//        });
//        buttonAdd.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                allCalculations();
//                currentSymbol = ADDITION;
//                outputDisplay.setText(firstValue + "+");
//                inputDisplay.setText(null);
//            }
//        });
//        buttonSub.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                allCalculations();
//                currentSymbol = SUBTRACTION;
//                outputDisplay.setText(firstValue + "-");
//                inputDisplay.setText(null);
//            }
//        });
//        buttonMultiply.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                allCalculations();
//                currentSymbol = MULTIPLICATION;
//                outputDisplay.setText(firstValue + "x");
//                inputDisplay.setText(null);
//            }
//        });
//        buttonDivide.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                allCalculations();
//                currentSymbol = DIVISION;
//                outputDisplay.setText(firstValue + "/");
//                inputDisplay.setText(null);
//            }
//        });
//
//        buttonDot.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                inputDisplay.setText(inputDisplay.getText() + ".");
//            }
//        });
//        buttonClear.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (inputDisplay.getText().length() > 0) {
//                    CharSequence currentText = inputDisplay.getText();
//                    inputDisplay.setText(currentText.subSequence(0, currentText.length() - 1));
//                } else {
//                    inputDisplay.setText("");
//                    outputDisplay.setText("");
//                }
//            }
//        });
//
//        buttonEqual.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                allCalculations();
//                // outputDisplay.setText(firstValue);
//                firstValue = Double.NaN;
//                currentSymbol = '0';
//            }
//        });
//    }
//    private void allCalculations(){
//        if (!Double.isNaN(firstValue)) {
//            secondValue = Double.parseDouble(inputDisplay.getText().toString());
//            inputDisplay.setText(null);
//            if (currentSymbol == ADDITION)
//                firstValue = this.firstValue + secondValue;
//            else if (currentSymbol == SUBTRACTION)
//                firstValue = this.firstValue - secondValue;
//            else if (currentSymbol == MULTIPLICATION)
//                firstValue = this.firstValue * secondValue;
//            else if (currentSymbol == DIVISION)
//                firstValue = this.firstValue / secondValue;
//        } else {
//            try {
//                firstValue = Double.parseDouble(inputDisplay.getText().toString());
//            } catch (Exception e) {
//            }
//        }
}
