package bankapp

// ATM interface for the BankApp
interface ATM {
    fun pinAuthentication(): Int {
        return "012345".toInt()
    }
} // still figuring out how to implement this authentication even though no polymorphism and inheritance in mind

class BankApp(val owner: String, var balanceProp: Double): ATM {

    // the most silly getter and setter ever by @Cavin
    var balance: Double = 0.0
        get() = if (balanceProp < 0.0) 0.0 else balanceProp

        set(value) {
            field = if (balanceProp < 0.0) 0.0 else value
        }

    // deposit
    fun deposit(amount: Double): Double {
        if (amount <= 0.0) {
            throw IllegalArgumentException("Amount must be more than 0.0")
        } else {
            balanceProp += amount
            println("$owner has deposited $amount into their bank account")
        }

        return balance
    }

    // withdraw
    fun withdraw(amount: Double): Double {
        try {
            if (amount > balance) {
                println("$owner you have insufficient balance to make this transaction")
            }  else if (amount <= 0.0) {
                throw IllegalArgumentException("Amount must be more than 0.0") // feel like this exception is not the right one to catch: I need to rethink about it and figure out the right one
            } else {
                balanceProp -= amount
                println("$owner has withdrawn $amount from their bank account")
            }
        } catch (e: ArithmeticException) { // I need to rethink the type of exception to throw here
            println("Error occurred while withdrawing $amount from your bank account, ${e.message}")
        }

        return balance
    }

    // check balance
    fun checkBalance() {
        println("$owner your balance is $balance")
    }
}

fun main() {
    val bankApp = BankApp("TestUser", -500.0) // the balance is an overdraft
    bankApp.checkBalance()

    bankApp.deposit(121.0)
    bankApp.checkBalance()

    bankApp.withdraw(30.0)

    bankApp.checkBalance()
    println(bankApp.pinAuthentication())
}

/*
Something is still missing in my overall logic **..***
 */