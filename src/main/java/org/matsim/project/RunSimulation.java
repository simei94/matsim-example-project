package org.matsim.project;

import com.google.inject.*;
import com.google.inject.Module;
import org.matsim.api.core.v01.Scenario;
import org.matsim.core.config.Config;
import org.matsim.core.config.ConfigUtils;
import org.matsim.core.controler.ControlerDefaultsModule;
import org.matsim.core.controler.NewControlerModule;
import org.matsim.core.controler.OutputDirectoryHierarchy;
import org.matsim.core.controler.corelisteners.ControlerDefaultCoreListenersModule;
import org.matsim.core.router.TripRouter;
import org.matsim.core.scenario.ScenarioByInstanceModule;
import org.matsim.core.scenario.ScenarioUtils;

public class RunSimulation {
    public static void main( String[] args ){
        Config config = ConfigUtils.createConfig();
        config.controler().setOverwriteFileSetting(OutputDirectoryHierarchy.OverwriteFileSetting.deleteDirectoryIfExists);

        Scenario scenario = ScenarioUtils.loadScenario(config);

        Module module = new org.matsim.core.controler.AbstractModule() {
            @Override
            public void install() {
                install(new NewControlerModule());
                install(new ControlerDefaultCoreListenersModule());
                install(new ControlerDefaultsModule());
                install(new ScenarioByInstanceModule(scenario));
            }
        };
//        Injector injector = Guice.createInjector(module);
//        Simulation simulation = injector.getInstance(Simulation.class);
//
//        simulation.doStep();

        Injector injector = org.matsim.core.controler.Injector.createInjector(config, module);

//        Simulation simulation = injector.getInstance(Simulation.class);
//
//        simulation.doStep();

        TripRouter tripRouter = injector.getInstance(TripRouter.class);
    }

    static interface Simulation {
        void doStep();
    }

    public static interface Helper {
        void help();
    }

    static class HelperDefaultImpl implements Helper {
        public void help() {
            System.out.println(this.getClass().getSimpleName() + " is helping");
        }
    }

    static class SimulationDefaultImpl implements Simulation {
        @Inject
        private Helper helper;

        public void doStep() {
            System.out.println("entering " + this.getClass().getSimpleName());
            helper.help();
            System.out.println("leaving " + this.getClass().getSimpleName());
        }
    }
}
