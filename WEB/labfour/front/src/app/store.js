import {createStore, applyMiddleware} from "redux";
import thunk from "redux-thunk"
import {composeWithDevTools} from "redux-devtools-extension";
import rootReducer from "../redusers/rootReducer";


export const store = createStore(
    rootReducer,
    composeWithDevTools(applyMiddleware(thunk))
)
