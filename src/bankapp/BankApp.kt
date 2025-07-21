package bankapp

import java.io.IO.readln


interface ATM {
    fun pinAuthentication(): Boolean
}

class BankApp(val owner: String, private val pin: String, initialBalance: Double) : ATM {
    var balance: Double = if (initialBalance < 0.0) 0.0 else initialBalance
        private set

    override fun pinAuthentication(): Boolean {
        print("Enter PIN: ")
        val inputPin: String = readln()
        return inputPin == pin // checking for equality not assignment or reference
    }

    fun deposit(amount: Double) {
        if (amount <= 0.0) {
            println("Amount must be greater than zero\n++++++++++++")
        } else {
            balance += amount
            println("Deposited $$amount\n++++++++++++")
        }
    }

    fun withdraw(amount: Double) {
        if (amount <= 0.0) {
            println("Amount must be greater than zero\n++++++++++++")
        } else if (amount > balance) {
            println("Insufficient balance\n++++++++++++")
        } else {
            balance -= amount
            println("Withdrew $$amount\n++++++++++++")
        }
    }

    fun checkBalance() {
        println("$owner, your balance is $balance\n++++++++++++")
    }
}

fun main() {
    val bankApp = BankApp("User", "1234", 0.0)

    if (bankApp.pinAuthentication()) {
        while (true) {
            println("1. Deposit\n2. Withdraw\n3. Check Balance\n4. Exit")
            when (readln("Enter your option: ")) {
                "1" -> {
                    print("Enter the amount to deposit: ")
                    bankApp.deposit(readln().toDouble())
                }
                "2" -> {
                        print("Enter the amount to withdraw: ")
                        bankApp.withdraw(readln().toDouble())
                    }
                "3" -> bankApp.checkBalance()
                "4" -> break
                else -> println("Invalid option choice.")
            }
        }
    } else {
        println("Incorrect PIN, exiting...")
    }
}