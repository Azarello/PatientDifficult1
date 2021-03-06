package furhatos.app.patientdifficult1.flow

import furhatos.nlu.common.*
import furhatos.flow.kotlin.*
import furhatos.app.patientdifficult1.nlu.*

import furhatos.flow.kotlin.State
import furhatos.flow.kotlin.furhat
import furhatos.flow.kotlin.onResponse
import furhatos.flow.kotlin.state
import furhatos.nlu.common.No
import java.util.*



val Start : State = state(Interaction) {

    onEntry {

        furhat.ask("Hello and welcome to this program, in which you will learn more about Intensive Short-Term " +
                "Dynamic Psychotherapy. The first step in the therapeutic process, is to establish a conscious " +
                "therapeutic alliance. The following program will guide you through different modules that train " +
                "the skills necessary in establishing this alliance. When you are ready to start the first module, " +
                " say start.")

    }

    onResponse<Continue>{
        goto(FirstModule)
    }

    onResponse<No> {
        furhat.say("Ok, restart me when you are ready to begin")
        goto(Idle)
    }
}

val FirstModule1 : State = state {
    onEntry {
        goto(FirstModule)
    }
}

val FirstModule : State = state {
    onEntry {
        furhat.say("The first step in establishing a conscious therapeutic alliance, is to get the patient to " +
                "declare an internal emotional problem, meaning they explicitly state an issue they are struggling" +
                " with. It is important for both patient and therapist to have a clear " +
                " picture of the issue to start therapy. Patients are often reluctant to explicitly state their " +
                "issue immediately, and use various defense mechanisms to avoid talking about the problem. ")
        furhat.say("The defense mechanisms can be divided into different categories depending on their structure. " +
                " In this module you will learn to" +
                " identify different kinds of defenses, which is the first step in learning to deal with them appropriately.  " +
                "The module is complete once you have correctly identified five defenses, resulting in the " +
                "patient clearly stating their problem. ")
        furhat.ask("Say continue if you are ready to start, or say repeat if you would like to hear the " +
                "instructions again")
    }



    onResponse<Continue> {
        furhat.say("Ok, this will be the first defense.")
        goto(DeclareProblem)
    }

    onResponse<Repeat> {
        goto(FirstModule1)
    }
}

val DeclareWait : State = state {
    onTime(delay=1500) {
        goto(DeclareProblem)
    }
}

val DeclareProblem : State = state {

    val rand = Random()
    val num = rand.nextInt(5)
    onEntry {
        when (num) {
            0 -> goto(Vague1)
            1 -> goto(Denial1)
            2 -> goto(Projection1)
            3 -> goto(CoverWord1)
            4 -> goto(Rationalization1)
        }
    }
}

var counter1 = 0

val Counter1 : State = state {



    onEntry {
        counter1 += 1
        if (counter1 < 5) {
            furhat.say(" Let's move on to the next defense")
            goto(DeclareProblem) }
        else {
            furhat.say("My problem is that I get very angry with my father sometimes when we speak")
            goto(Resolution1)

        }
    }
}
val Wait1 : State = state {

    onTime(delay=1500) {
        furhat.ask("When you are ready for the next defense, say next")
    }

    onResponse<Continue> {
        goto(Counter1)
    }
}

val Vague1Goto : State = state {
    onEntry {
        goto(Vague1)
    }
}

val Vague1 : State = state {

    val rand = Random()
    val num = rand.nextInt(5)
    onEntry {
        when (num) {
            0 -> furhat.ask("I've just been feeling kind of down lately and not sure what is the matter")
            1 -> furhat.ask("I've been having these episodes for a while that are sort of a problem")
            2 -> furhat.ask("It's something to do with my father, just stuff going on that's complicated")
            3 -> furhat.ask("It's just these things happening in my life you know")
            4 -> furhat.ask("Some issues have been coming up that I'm not sure about")
        }
    }

    onResponse<VagueBlock1> {

        it.intent.specific
        it.intent.avoid
        it.intent.notice
        it.intent.negative
        it.intent.person
        it.intent.problem
        it.intent.feel


    }


    onResponse<DenialBlock1> {
        goto(DeclareProblem)
    }

    onResponse<ProjectionBlock1> {

        goto(DeclareProblem)
    }

    onResponse<CoverWordBlock1> {

        goto(DeclareProblem)
    }

    onResponse<RationalizationBlock1> {

        goto(DeclareProblem)
    }

}



val Denial1Goto : State = state {
    onEntry {
        goto(Denial1)
    }

}

val Denial1 : State = state {

    val rand = Random()
    val num = rand.nextInt(5)
    onEntry {
        when (num) {
            0 -> furhat.ask(" Actually I don't have a problem that merits so much attention, not sure what I'm doing here")
            1 -> furhat.ask(" It's really not a big deal, I'm not bothered by it most of the time")
            2 -> furhat.ask(" I'm not really feeling all that bad when I think about it, it's just a minor thing")
            3 -> furhat.ask(" It seemed a lot worse before, now it doesn't strike me as very troubling or important")
            4 -> furhat.ask(" I've just been a little off, but it's not really a major issue")
        }
    }

    onResponse<DenialBlock1> {

        it.intent.deny
        it.intent.feel
        it.intent.notice
        it.intent.specific
        it.intent.problem
        it.intent.avoid

        goto(Counter1)
    }


    onResponse<VagueBlock1> {

        goto(DeclareProblem)
    }

    onResponse<ProjectionBlock1> {

        goto(DeclareProblem)
    }

    onResponse<CoverWordBlock1> {

        goto(DeclareProblem)
    }

    onResponse<RationalizationBlock1> {

        goto(DeclareProblem)
    }
}


val Projection1Goto : State = state {
    onEntry {
        goto(Projection1)
    }
}

val Projection1 : State = state {

    val rand = Random()
    val num = rand.nextInt(5)
    onEntry {
        when (num) {
            0 -> furhat.ask(" Well I don't think it's a problem, my wife made me come here")
            1 -> furhat.ask(" Actually it was my doctor's idea for me to come here after my medication didn't help")
            2 -> furhat.ask(" You sure seem to think I have a problem that needs attention")
            3 -> furhat.ask(" My son thought I should see someone so I'm really doing it for his sake")
            4 ->furhat.ask("If my dad weren't annoying I wouldn't need to come here, it's really him with the issues")
        }
    }

    onResponse<ProjectionBlock1> {

        it.intent.person
        it.intent.problem
        it.intent.avoid
        it.intent.will
        it.intent.force
        it.intent.intellect
        it.intent.obey
        it.intent.specify
        it.intent.feel
        it.intent.notice

        goto(Counter1)
    }







    onResponse<VagueBlock1> {

        goto(DeclareProblem)
    }

    onResponse<DenialBlock1> {

        goto(DeclareProblem)
    }

    onResponse<CoverWordBlock1> {
        goto(DeclareProblem)
    }

    onResponse<RationalizationBlock1> {
        goto(DeclareProblem)
    }



}

val CoverWord1Goto : State = state {
    onEntry {
        goto(CoverWord1)
    }
}

val CoverWord1 : State = state {

    val rand = Random()
    val num = rand.nextInt(5)
    onEntry {
        when (num) {
            0 -> furhat.ask("I've been feeling a bit infuriated about my dad")
            1 -> furhat.ask(" It's just kind of annoying when he does the same thing all the time")
            2 -> furhat.ask(" My dad has this way of making me slightly upset when we talk")
            3 -> furhat.ask(" There are these situations when I feel rather disconcerted")
            4 -> furhat.ask(" He has a way of talking that's just rather bothersome and gets to me")
        }
    }

    onResponse<CoverWordBlock1> {

        it.intent.person
        it.intent.cover
        it.intent.negative
        it.intent.feel
        it.intent.notice
        it.intent.specify

        goto(Counter1)
    }


    onResponse<VagueBlock1> {
        goto(DeclareProblem)
    }

    onResponse<DenialBlock1> {
        goto(DeclareProblem)
    }

    onResponse<ProjectionBlock1> {
        goto(DeclareProblem)
    }

    onResponse<RationalizationBlock1> {
        goto(DeclareProblem)
    }
}


val Rationalization1Goto : State = state {
    onEntry {
        goto(Rationalization1)
    }
}

val Rationalization1 : State = state {

    val rand = Random()
    val num = rand.nextInt(5)
    onEntry {
        when (num) {
            0 -> furhat.ask(" If my dad just didn't bring up certain topics I wouldn't get upset")
            1 -> furhat.ask(" I think the reason for my problem stems from when I was younger")
            2 -> furhat.ask(" I think it's because he has this strange voice that I get needlessly upset")
            3 -> furhat.ask(" It's only when I'm tired that I experience these problems so I just need to get more sleep")
            4 -> furhat.ask(" I noticed that my problem comes up whenever I'm also stressed from work so that might be a reason")
        }
    }

    onResponse<RationalizationBlock1> {

        it.intent.intellect
        it.intent.feel
        it.intent.notice
        it.intent.avoid
        it.intent.person
        it.intent.negative
        it.intent.problem

        goto(Counter1)
    }


    onResponse<VagueBlock1> {
        goto(DeclareProblem)
    }

    onResponse<DenialBlock1> {
        goto(DeclareProblem)
    }

    onResponse<ProjectionBlock1> {
        goto(DeclareProblem)
    }

    onResponse<CoverWordBlock1> {
        goto(DeclareProblem)
    }
}


val Resolution1 : State = state {

    onEntry {
        furhat.ask(" Great job! You got the patient to declare an internal problem which, is the anger they " +
                "experience when talking to their father. You have successfully completed the first module and first " +
                "part of establishing a therapeutic alliance. If you would like to go over this module again say repeat. ")
    }

    onResponse<Repeat> {
        counter1 = 0
        goto(FirstModule)
    }

}