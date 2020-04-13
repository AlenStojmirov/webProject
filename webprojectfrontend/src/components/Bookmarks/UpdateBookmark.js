import React, {Component} from "react";
import Navbar from "../Navbar/Navbar";
import {getBookmark,createBookmark} from "../../actions/bookmarkActions";
import PropTypes from "prop-types";
import {connect} from "react-redux";
import classnames from "classnames";

class UpdateBookmark extends Component {

    constructor() {
        super();

        this.state={
            id:"",
            name:"",
            rating:0,
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
            name,
            rating
        }= nextProps.bookmark;

        this.setState({
            id,
            name,
            rating
        });

    };

    componentDidMount(){
        const {id} = this.props.match.params;
        this.props.getBookmark(id,this.props.history);
    }

    onChange(e){
        this.setState({[e.target.name]:e.target.value})
    }

    onSubmit(e){
        e.preventDefault();

        const updateBookmark={
            id:this.state.id,
            name:this.state.name,
            rating:this.state.rating
        };

        this.props.createBookmark(updateBookmark,this.props.history);
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
                                    aria-describedby="Bookmark Name" placeholder="Bookmark Name"/>
                                {
                                    errors.name &&(
                                        <div className="invalid-feedback">{errors.name}</div>
                                    )
                                }
                                <input
                                    className={classnames("form-control my-4",{
                                        "is-invalid":errors.rating
                                    })}
                                    name="rating"
                                    value={this.state.rating}
                                    onChange={this.onChange}
                                    aria-describedby="Rating" placeholder="Rating"/>
                                {
                                    errors.rating &&(
                                        <div className="invalid-feedback">{errors.rating}</div>
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

UpdateBookmark.propTypes = {
    getBookmark: PropTypes.func.isRequired,
    createBookmark: PropTypes.func.isRequired,
    bookmark:PropTypes.object.isRequired,
    errors: PropTypes.object.isRequired
};

const mapStateToProps = state => ({
    bookmark: state.bookmark.bookmark,
    errors: state.errors
});

export default connect(mapStateToProps,{getBookmark, createBookmark}) (UpdateBookmark);
