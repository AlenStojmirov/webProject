import React, {Component} from 'react';
import Navbar from "../Navbar/Navbar";
import PropTypes from "prop-types";
import {connect} from "react-redux";
import {createLeague} from "../../actions/leagueActions";
import axios from "axios";

class CreateLeague extends Component {

    constructor(){
        super();

        this.state={
            name:"",
            sport:{},
            clubs:{},
            sports:[],
            errors:{}

        };

        this.onChange = this.onChange.bind(this);
        this.onSubmit = this.onSubmit.bind(this);
    }

    componentDidMount(){
        axios.get("/sports").then((response) =>{
            this.setState({sports:response.data})
        });
    }

    //life cycle hooks
    componentWillReceiveProps(nextProps, nextContext) {
        if(nextProps.errors){
            this.setState({errors:nextProps.errors})
        }

    }

    onChange(e){
        this.setState({[e.target.name] : e.target.value});
    }

    onSubmit(e){
        e.preventDefault();
        const newLeague={
            "name":this.state.name,
            "sport":this.state.sport,
            "clubs":{},
        };
        this.props.createLeague(newLeague,this.props.history)
    }

    render(){

        const {errors} = this.state;

        return(<div>

            <Navbar />
            <div className="container text-center my-5">
                <div className="col-md-4 m-auto">
                    <form onSubmit={this.onSubmit} className="align-content-center">
                        <div className="form-group">
                            <label className="text-danger">{errors.name}</label>
                            <input
                                className="form-control"
                                name="name"
                                value={this.state.name}
                                onChange={this.onChange}
                                aria-describedby="League Name" placeholder="League Name"/>

                            <select className="form-control" name="sport" onChange={this.onChange}>
                                {this.state.sports.map(sport =>(
                                    <option key={sport.id}>{sport.name}</option>
                                    ))
                                }
                            </select>
                        </div>
                        <button type="submit" className="btn btn-warning ">Create</button>
                    </form>
                </div>
            </div>

        </div>)
    }
}

CreateLeague.propTypes = {
    createLeague: PropTypes.func.isRequired,
    errors: PropTypes.object.isRequired
};

const mapStateToProps = state => ({
    errors: state.errors
});

export default connect(mapStateToProps,{createLeague}) (CreateLeague);