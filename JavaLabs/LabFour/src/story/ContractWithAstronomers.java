package story;

import utils.*;

public class ContractWithAstronomers implements Contract {
    protected Person client;
    protected Astronomer astronomer;
    protected Thing promisedThing;
    protected boolean contractIsFinished = false;

    public ContractWithAstronomers(Person client, Astronomer astronomer, Thing thing) {
        this.client = client;
        this.astronomer = astronomer;
        this.promisedThing = thing;
    }

    @Override
    public void finishContract() throws ContractAlreadyCompleted {
        if (contractIsFinished) {
            throw new ContractAlreadyCompleted();
        }
        astronomer.say(client, "Обнаружен искомый объект в зоне наблюдения.");
        System.out.println(client + " передал " + astronomer + " обещанный предмет " + promisedThing.getName());
        contractIsFinished = true;
    }
}
