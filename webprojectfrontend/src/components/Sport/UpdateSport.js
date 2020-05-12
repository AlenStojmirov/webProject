import React, {Component} from "react";
import Navbar from "../Navbar/Navbar";
import {getSport,createSport} from "../../actions/sportActions";
import PropTypes from "prop-types";
import {connect} from "react-redux";
import classnames from "classnames";

class UpdateSport extends Component {

    constructor() {
        super();

        this.state={
            id:"",
            name:"",
            errors:{}
        };
        this.onChange = this.onChange.bind(this);
        this.onSubmit = this.onSubmit.bind(this);
    }

    componentWillReceiveProps(nextProps, nextContext) {

        if(nextProps.errors){
            this.setState({errors:nextProps.errors})
        }

        const{
            id,
            name
        }= nextProps.sport;

        this.setState({
            id,
            name
        });

    };

    componentDidMount(){
        const {id} = this.props.match.params;
        this.props.getSport(id,this.props.history);
    }

    onChange(e){
        this.setState({[e.target.name]:e.target.value})
    }

    onSubmit(e){
        e.preventDefault();

        const updateSport={
            id:this.state.id,
            name:this.state.name
        };

        this.props.createSport(updateSport,this.props.history);
    }

    render() {
        const {errors} = this.props;
        return (
            <div>
                <Navbar />
                <div className="container text-center my-5">
                    <div className="col-md-4 m-auto">
                        <form onSubmit={this.onSubmit} className="align-content-center">
                            <div className="form-group">
                                <input
                                    className={classnames("form-control my-4",{
                                        "is-invalid":errors.name
                                    })}
                                    name="name"
                                    value={this.state.name}
                                    onChange={this.onChange}
                                    aria-describedby="Sport Name" placeholder="Sport Name"/>
                                {
                                    errors.name &&(
                                        <div className="invalid-feedback">{errors.name}</div>
                                    )
                                }
                            </div>
                            <button type="submit" className="btn btn-warning ">Save</button>
                        </form>
                    </div>
                </div>

            </div>
        );
    }
}

UpdateSport.propTypes = {
    getSport: PropTypes.func.isRequired,
    createSport: PropTypes.func.isRequired,
    sport:PropTypes.object.isRequired,
    errors: PropTypes.object.isRequired
};

const mapStateToProps = state => ({
    sport: state.sport.sport,
    errors: state.errors
});

export default connect(mapStateToProps,{getSport, createSport}) (UpdateSport);
