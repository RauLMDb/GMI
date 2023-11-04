import chalk from "chalk";
import { p1 } from "./sintactico.js"

export function sintactico(){
   p1()
   console.log(chalk.bold(chalk.green("Archivo procesado con éxito")));
}