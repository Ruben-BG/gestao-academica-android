package com.ravtec.gestaoacademica

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.provider.BaseColumns
import androidx.core.database.getIntOrNull

class UsuariosDB(context: Context) : SQLiteOpenHelper(context, "bd_usuarios", null, 3) {


    object UsuariosEntry : BaseColumns {
        const val TABLE_NAME = "usuarios"
        const val COLUMN_NAME_NOME = "nome"
        const val COLUMN_NAME_NOMEUSUARIO = "nomeusuario"
        const val COLUMN_NAME_CPF = "cpf"
        const val COLUMN_NAME_EMAIL = "email"
        const val COLUMN_NAME_SENHA = "senha"
        const val COLUMN_NAME_TELEFONE = "telefone"
        const val COLUMN_NAME_ENDERECO = "endereco"
        const val COLUMN_NAME_MATRICULA = "matricula"
    }

    private val SQL_CREATE_ENTRIES =
        "CREATE TABLE IF NOT EXISTS ${UsuariosEntry.TABLE_NAME} (" +
                "${UsuariosEntry.COLUMN_NAME_NOME} TEXT," +
                "${UsuariosEntry.COLUMN_NAME_NOMEUSUARIO} TEXT," +
                "${UsuariosEntry.COLUMN_NAME_CPF} TEXT PRIMARY KEY," +
                "${UsuariosEntry.COLUMN_NAME_EMAIL} TEXT," +
                "${UsuariosEntry.COLUMN_NAME_SENHA} TEXT," +
                "${UsuariosEntry.COLUMN_NAME_TELEFONE} TEXT," +
                "${UsuariosEntry.COLUMN_NAME_ENDERECO} TEXT," +
                "${UsuariosEntry.COLUMN_NAME_MATRICULA} INTEGER)"

    private val SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS usuarios"


    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(SQL_CREATE_ENTRIES)
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        db?.execSQL(SQL_DELETE_ENTRIES)
        onCreate(db)
    }

    override fun onDowngrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        onUpgrade(db, oldVersion, newVersion)
    }

    fun adicionarCoordenador(usuarioCoordenador: UsuarioCoordenador) {

        val db = this.writableDatabase

        val valores = ContentValues().apply {
            put(UsuariosEntry.COLUMN_NAME_NOME, usuarioCoordenador.nome)
            put(UsuariosEntry.COLUMN_NAME_NOMEUSUARIO, usuarioCoordenador.nomeDeUsuario)
            put(UsuariosEntry.COLUMN_NAME_CPF, usuarioCoordenador.cpf)
            put(UsuariosEntry.COLUMN_NAME_EMAIL, usuarioCoordenador.email)
            put(UsuariosEntry.COLUMN_NAME_SENHA, usuarioCoordenador.senha)
            put(UsuariosEntry.COLUMN_NAME_TELEFONE, usuarioCoordenador.telefone)
            put(UsuariosEntry.COLUMN_NAME_ENDERECO, usuarioCoordenador.endereco)
        }
        db.insert(UsuariosEntry.TABLE_NAME, null, valores)

    }

    @SuppressLint("Recycle")
    fun adicionarProfessorAluno(usuario: Usuario) {

        val db = this.writableDatabase

        val cursor = this.readableDatabase.query(
            UsuariosEntry.TABLE_NAME,
            arrayOf(UsuariosEntry.COLUMN_NAME_NOME, UsuariosEntry.COLUMN_NAME_EMAIL, UsuariosEntry.COLUMN_NAME_MATRICULA),
            null,
            null,
            null,
            null,
            "${UsuariosEntry.COLUMN_NAME_MATRICULA} ASC"
        )

        var ultimaMatricula = 0
        with(cursor) {
            while (moveToNext()) {
                ultimaMatricula = if (getIntOrNull(getColumnIndexOrThrow(UsuariosEntry.COLUMN_NAME_MATRICULA)) == null) 0 else getInt(getColumnIndexOrThrow(UsuariosEntry.COLUMN_NAME_MATRICULA))
            }
        }
        cursor.close()

        val valores = ContentValues().apply {
            put(UsuariosEntry.COLUMN_NAME_NOME, usuario.nome)
            put(UsuariosEntry.COLUMN_NAME_CPF, usuario.cpf)
            put(UsuariosEntry.COLUMN_NAME_EMAIL, usuario.email)
            put(UsuariosEntry.COLUMN_NAME_SENHA, usuario.senha)
            put(UsuariosEntry.COLUMN_NAME_TELEFONE, usuario.telefone)
            put(UsuariosEntry.COLUMN_NAME_ENDERECO, usuario.endereco)
            put(UsuariosEntry.COLUMN_NAME_MATRICULA, ultimaMatricula)
        }

        db.insert(UsuariosEntry.TABLE_NAME, null, valores)

    }

    fun autenticarUsuarioCoordenador(nomeDeUsuario: String, senha: String): Boolean {

        val db = this.readableDatabase
        val cursor = db.query(
            UsuariosEntry.TABLE_NAME,
            arrayOf(UsuariosEntry.COLUMN_NAME_NOMEUSUARIO, UsuariosEntry.COLUMN_NAME_SENHA),
            null,
            null,
            null,
            null,
            "${UsuariosEntry.COLUMN_NAME_NOMEUSUARIO} ASC"
        )

        with(cursor) {
            while (moveToNext()) {
                if (getString(getColumnIndexOrThrow(UsuariosEntry.COLUMN_NAME_NOMEUSUARIO)) == nomeDeUsuario &&
                    getString(getColumnIndexOrThrow(UsuariosEntry.COLUMN_NAME_SENHA)) == senha)
                    return true
            }
        }
        cursor.close()
        return false

    }

    fun autenticarUsuarioAlunoProfessor(matricula: Int, senha: String): Boolean {

        val db = this.readableDatabase
        val cursor = db.query(
            UsuariosEntry.TABLE_NAME,
            arrayOf(UsuariosEntry.COLUMN_NAME_MATRICULA, UsuariosEntry.COLUMN_NAME_SENHA),
            null,
            null,
            null,
            null,
            "${UsuariosEntry.COLUMN_NAME_MATRICULA} ASC"
        )

        with(cursor) {
            while (moveToNext()) {
                if (getInt(getColumnIndexOrThrow(UsuariosEntry.COLUMN_NAME_MATRICULA)) == matricula &&
                    getString(getColumnIndexOrThrow(UsuariosEntry.COLUMN_NAME_SENHA)) == senha)
                    return true
            }
        }
        cursor.close()
        return false

    }

    fun verificarEmailExistente(email: String): Boolean {

        val db = this.readableDatabase
        val cursor = db.query(
            UsuariosEntry.TABLE_NAME,
            arrayOf(UsuariosEntry.COLUMN_NAME_EMAIL),
            null,
            null,
            null,
            null,
            "${UsuariosEntry.COLUMN_NAME_EMAIL} ASC"
        )

        with(cursor) {
            while (moveToNext()) {

                if (email == getString(getColumnIndexOrThrow(UsuariosEntry.COLUMN_NAME_EMAIL))) return true

            }
        }
        cursor.close()

        return false

    }

    fun verificarNomeDeUsuarioExistente(nomeDeUsuario: String): Boolean {

        val db = this.readableDatabase
        val cursor = db.query(
            UsuariosEntry.TABLE_NAME,
            arrayOf(UsuariosEntry.COLUMN_NAME_NOMEUSUARIO),
            null,
            null,
            null,
            null,
            "${UsuariosEntry.COLUMN_NAME_MATRICULA} ASC"
        )

        with(cursor) {
            while (moveToNext()) {
                if (getString(getColumnIndexOrThrow(UsuariosEntry.COLUMN_NAME_NOMEUSUARIO)) == nomeDeUsuario) return true
            }
        }
        cursor.close()

        return false

    }

    fun pegarUsuarioCoordenador(nomeDeUsuario: String): UsuarioCoordenador {

        val db = this.readableDatabase
        val cursor = db.query(
            UsuariosEntry.TABLE_NAME,
            arrayOf(UsuariosEntry.COLUMN_NAME_NOME, UsuariosEntry.COLUMN_NAME_NOMEUSUARIO, UsuariosEntry.COLUMN_NAME_CPF, UsuariosEntry.COLUMN_NAME_EMAIL, UsuariosEntry.COLUMN_NAME_SENHA, UsuariosEntry.COLUMN_NAME_TELEFONE, UsuariosEntry.COLUMN_NAME_ENDERECO),
            null,
            null,
            null,
            null,
            "${UsuariosEntry.COLUMN_NAME_NOMEUSUARIO} ASC"
        )

        var nome = ""
        var cpf = ""
        var email = ""
        var senha = ""
        var telefone = ""
        var endereco = ""

        with(cursor) {
            while (moveToNext()) {

                if (getString(getColumnIndexOrThrow(UsuariosEntry.COLUMN_NAME_NOMEUSUARIO)) == nomeDeUsuario) {
                    nome = getString(getColumnIndexOrThrow(UsuariosEntry.COLUMN_NAME_NOME))
                    cpf = getString(getColumnIndexOrThrow(UsuariosEntry.COLUMN_NAME_CPF))
                    email = getString(getColumnIndexOrThrow(UsuariosEntry.COLUMN_NAME_EMAIL))
                    senha = getString(getColumnIndexOrThrow(UsuariosEntry.COLUMN_NAME_SENHA))
                    telefone = getString(getColumnIndexOrThrow(UsuariosEntry.COLUMN_NAME_TELEFONE))
                    endereco = getString(getColumnIndexOrThrow(UsuariosEntry.COLUMN_NAME_ENDERECO))
                }

            }
        }
        cursor.close()

        return UsuarioCoordenador(nome, cpf, email, senha, telefone, endereco, nomeDeUsuario)

    }

}