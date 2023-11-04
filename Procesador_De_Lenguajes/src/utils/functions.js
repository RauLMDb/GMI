import { writeFile } from "fs"
import { tablaSimbolos, tokens, parse } from "../databases/data.js"
import chalk from "chalk";

export function outputTokens(){
    let res = ""

    // Procesamos todos los tokens con el formato pedido
    tokens.forEach(( tk ) => {
        let atr = tk.atr == null ? "" : tk.atr;
        res += "<"+tk.id+","+atr+">\n";
    });

    // Volcamos sobre el output
    writeFile(process.cwd() + "/out/tokens.txt", res, function(err) {
        if(err) return console.log(err);
        console.log(chalk.bgGreen(chalk.bold(chalk.gray("[INFO]"))), chalk.green("Tokens volcados en el archivo `tokens.txt`"));
    });
}

export function outputTS(){
    let res = ""

    // Procesamos todos los tokens con el formato pedido
    tablaSimbolos.forEach(( ts, index ) => {
        res += "TABLA " + ts.name + " #" + index + " :\n"
        ts.tabla.forEach(( row ) => {
            res += "* LEXEMA : '" + row.lexema + "'\n"
            if (row.tipo != null) res += "  + tipo : '" + row.tipo + "'\n"
            if (row.despl != null) res += "  + despl : " + row.despl + "\n"
            if (row.numParams != null) res += "  + numParam : " + row.numParams + "\n"
            if (row.params.length != 0) {
                row.params.forEach((e, i) => {
                    res += "   + TipoParam" + (i + 1) + " : '" + e + "'\n"
                    // res += "    + ModoParam" + (i + 1) + " : " + (i + 1) + "\n"
                })
            }
            if (row.tipoRetorno != null) res += "  + TipoRetorno : '" + row.tipoRetorno + "'\n"
            if (row.etiqRetorno != null) res += "  + EtiqFuncion : '" + row.etiqRetorno + "'\n"
            if (row.param != null) res += "  + Param : '" + row.param + "'\n"
        })
    });


    // Volcamos sobre el output
    writeFile(process.cwd() + "/out/tablaSimbolos.txt", res, function(err) {
        if(err) return console.log(err);
        console.log(chalk.bgGreen(chalk.bold(chalk.gray("[INFO]"))), chalk.green("Tokens volcados en el archivo `tablaSimbolos.txt`"));
    });
}
export function outputParse(){
    let res = "Des "

    // Procesamos todos los tokens con el formato pedido
    parse.forEach(( p ) => {
        res += p+" ";
    });

    // Volcamos sobre el output
    writeFile(process.cwd() + "/out/parse.txt", res, function(err) {
        if(err) return console.log(err);
        console.log(chalk.bgGreen(chalk.bold(chalk.gray("[INFO]"))), chalk.green("Parse volcado en el archivo `parse.txt`"));
    });
}