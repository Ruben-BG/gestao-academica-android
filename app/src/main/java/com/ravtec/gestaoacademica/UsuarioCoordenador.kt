package com.ravtec.gestaoacademica

class UsuarioCoordenador(
    override var nome: String,
    override var cpf: String,
    override var email: String,
    override var senha: String,
    override var telefone: String,
    override var endereco: String,
    var nomeDeUsuario: String
) :
    Usuario(nome, cpf, email, senha, telefone, endereco) {

}