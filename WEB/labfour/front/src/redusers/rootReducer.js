import {combineReducers} from "redux";
import {hitInfos} from "./hitInfos";


const rootReducer = combineReducers({
    hitInfos: hitInfos
})

export default rootReducer