package furhatos.app.patientdifficult1

import furhatos.app.patientdifficult1.flow.*
import furhatos.skills.Skill
import furhatos.flow.kotlin.*

class Patientdifficult1Skill : Skill() {
    override fun start() {
        Flow().run(Idle)
    }
}

fun main(args: Array<String>) {
    Skill.main(args)
}
