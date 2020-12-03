class Validation {
    constructor(
        form,
        settings // { required: false, minLength: null, maxLength: null, fileTypes: [], min: null, max: null }
    ) {
        this.form = form;

        if (settings) {
            this.settings = {
                required: settings.required !== undefined ? settings.required : false,
                minLength: settings.minLength !== undefined ? settings.minLength : null,
                maxLength: settings.maxLength !== undefined ? settings.maxLength : null,
                min: settings.min !== undefined ? settings.min : null,
                max: settings.max !== undefined ? settings.max : null,
                laterThan: settings.laterThan !== undefined ? settings.laterThan : null,
                identity: settings.identity !== undefined ? settings.identity : null,
                regex: settings.regex !== undefined ? settings.regex : null
            }
        } else {
            this.settings = {
                required: false,
                minLength: null,
                maxLength: null,
                min: null,
                max: null,
                regex: null
            }
        }
    }

    static createError(validationError) {
        const errorNode = document.createElement('div');
        errorNode.classList.add('custom-validation-error');

        const errorIcon = document.createElement('i');
        errorIcon.classList.add('fas', 'fa-exclamation-circle', 'custom-validation-error__icon');

        const errorText = document.createElement('div');
        errorText.classList.add('custom-validation-error__text');
        errorText.textContent = validationError;

        errorNode.append(errorIcon, errorText);

        return {
            node: errorNode,
            text: errorText
        }
    }

    validate(value) {
        if (this.settings.required && !Validation.validateRequired(value)) {
            return ValidationError.REQUIRED_VALIDATION_ERROR();
        }
        if (this.settings.minLength !== null && !Validation.validateMinLength(value, this.settings.minLength)) {
            return ValidationError.MIN_LENGTH_VALIDATION_ERROR(this.settings.minLength);
        }
        if (this.settings.min !== null && !Validation.validateMin(value, this.settings.min)) {
            return ValidationError.MIN_VALIDATION_ERROR(this.settings.min);
        }
        if (this.settings.max !== null && !Validation.validateMax(value, this.settings.max)) {
            return ValidationError.MAX_VALIDATION_ERROR(this.settings.max);
        }
        if (this.settings.laterThan !== null) {
            const laterThan = this.form.searchFormItemByName(this.settings.laterThan);
            if (laterThan !== null && laterThan.getValue() !== null && !Validation.validateLaterThan(value, laterThan.getValue())) {
                return ValidationError.LATER_THAN_VALIDATION_ERROR(laterThan.getValue());
            }
        }
        if (this.settings.identity !== null) {
            const identityItem = this.form.searchFormItemByName(this.settings.identity);
            if (identityItem !== null && identityItem.getValue() !== null && !Validation.validateIdentity(value, identityItem.getValue())) {
                return ValidationError.IDENTITY_VALIDATION_ERROR(identityItem.attributes.label);
            }
        }
        if (this.settings.regex !== null && !Validation.validateRegex(value, this.settings.regex)) {
            return ValidationError.REGEX_VALIDATION_ERROR(this.settings.regex);
        }
        return null;
    }

    static validateRequired(value) {
        if (value) {
            if (value instanceof Array) {
                return value.length;
            }
            return value !== '';
        }
        return false;
    }

    static validateMinLength(value, minLength) {
        return (value && minLength) || value.length >= minLength;
    }

    static validateMin(value, min) {
        return value >= min;
    }

    static validateMax(value, max) {
        return value <= max;
    }

    static validateLaterThan(value, laterThan) {
        return new Date(value) - new Date(laterThan) > 0;
    }

    static validateIdentity(value, identityValue) {
        return value === identityValue;
    }

    static validateRegex(value, regex) {
        return value.match(new RegExp(regex, 'g')) != null;
    }
}