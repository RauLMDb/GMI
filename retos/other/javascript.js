const suma = (a, b) => a + b;
const resta = (a, b) => a - b;
const multiplicacion = (a, b) => a * b;
const division = (a, b) => a / b;

const body = document.getElementById("body");
const cero = document.getElementById("cero");
cero.style.position = 'relative';
cero.style.top = 350 + 'px';
cero.style.left = -30 + '%';
cero.style.backgroundColor = '#3498db';
cero.style.color = 'white';
cero.style.padding = '10px 20px';
cero.style.border = '#000508 2px solid';
cero.style.borderRadius = '10px';
cero.style.cursor = 'pointer';

let x = 0, y =  0, operacion, cont = 0;
const resultado = document.getElementById("resultado");
const equal = () => {
  switch (operacion) {
    case '+':
      resultado.innerText = suma(x, y);
      break;
    case '-':
      resultado.innerText = resta(x, y);
      break;
    case '*':
      resultado.innerText = multiplicacion(x, y);
      break;
    case '/':
      resultado.innerText = division(x, y);
      break;
    default:
      console.log("Error");
      break;
  }
}
const button = (n) => {
  if (cont <= 1)
    {
      resultado.innerText += n;
    }
  let m = parseInt(n);
  if (!isNaN(m)) {
    if (cont === 1) {
      y = y * 10 + m;
      console.log("y = "+y);
      cont = 3
    }
    else if (cont === 0){
      x = x * 10 + m;
      console.log("x = "+x);
    }

  } else if (cont < 1){ 
    operacion = n; 
    cont++; 
    console.log("operacion = "+operacion);
  }
}
const equalButton = document.getElementById("equal");
equalButton.addEventListener("click", equal);
const ceroButton = document.getElementById("cero");
ceroButton.addEventListener("click", () => button(0));
const oneButton = document.getElementById("one");
oneButton.addEventListener("click", () => button(1));
const twoButton = document.getElementById("two");
twoButton.addEventListener("click", () => button(2));
const threeButton = document.getElementById("three");
threeButton.addEventListener("click", () => button(3));
const fourButton = document.getElementById("four");
fourButton.addEventListener("click", () => button(4));
const fiveButton = document.getElementById("five");
fiveButton.addEventListener("click", () => button(5));
const sixButton = document.getElementById("six");
sixButton.addEventListener("click", () => button(6));
const sevenButton = document.getElementById("seven");
sevenButton.addEventListener("click", () => button(7));
const eightButton = document.getElementById("eight");
eightButton.addEventListener("click", () => button(8));
const nineButton = document.getElementById("nine");
nineButton.addEventListener("click", () => button(9));
const plusButton = document.getElementById("plus");
plusButton.addEventListener("click", () => button('+'));
const minusButton = document.getElementById("minus");
minusButton.addEventListener("click", () => button('-'));
const multiplyButton = document.getElementById("multiply");
multiplyButton.addEventListener("click", () => button('*'));
const divideButton = document.getElementById("divide");
divideButton.addEventListener("click", () => button('/'));

const clearButton = document.createElement('button');
clearButton.innerText = "Clear";
clearButton.style.position = 'relative';
clearButton.style.top = 350 + 'px';
clearButton.style.left = -20 + '%';
clearButton.style.backgroundColor = '#3498db';
clearButton.style.color = 'white';
clearButton.style.padding = '10px 20px';
clearButton.style.border = '#000508 2px solid';
clearButton.style.borderRadius = '10px';
clearButton.style.cursor = 'pointer';
clearButton.addEventListener("click", () => {resultado.innerText = ""; x = 0; y = 0; cont = 0;operacion = "";});
document.getElementById("container").appendChild(clearButton);
