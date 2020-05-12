import React, {Component} from 'react';
import Navbar from "../Navbar/Navbar";
import PropTypes from "prop-types";
import {connect} from "react-redux";
import {createSport} from "../../actions/sportActions";

class CreateSport extends Component {

    constructor(){
        super();

        this.state={
            name:"",
            errors:{}
        };

        this.onChange = this.onChange.bind(this);
        this.onSubmit = this.onSubmit.bind(this);
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
        const newSport={
            "name":this.state.name
        };
        this.props.createSport(newSport,this.props.history)
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
                                aria-describedby="Sport Name" placeholder="Sport Name"/>
                        </div>
                        <button type="submit" className="btn btn-warning ">Create</button>
                    </form>
                </div>
            </div>

        </div>)
    }
}

CreateSport.propTypes = {
    createSport: PropTypes.func.isRequired,
    errors: PropTypes.object.isRequired
};

const mapStateToProps = state => ({
    errors: state.errors
});

export default connect(mapStateToProps,{createSport}) (CreateSport);