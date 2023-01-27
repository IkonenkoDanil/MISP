from django.utils import timezone
from rest_framework import serializers
from .models import DayStats, Note


class NoteSerializer(serializers.ModelSerializer):
    class Meta:
        model = Note
        fields = (
            'text',
            'emotion',
            'time'
        )
        read_only_fields = (
            'time',
        )

    def create(self, validated_data):
        today = timezone.localtime(timezone.now()).now().date()
        user = self.context['request'].user
        day_stats, _ = DayStats.objects.get_or_create(date=today, user=user)
        obj = Note.objects.create(day=day_stats, **validated_data)
        return obj


class DayStatsListSerializer(serializers.ModelSerializer):
    class Meta:
        model = DayStats
        fields = (
            'id',
            'text',
            'date',
            'overall_emotion'
        )
        read_only_fields = (
            'id',
            'date',
            'overall_emotion'
        )

    overall_emotion = serializers.CharField(read_only=True)


class DayStatsDetailSerializer(serializers.ModelSerializer):
    class Meta:
        model = DayStats
        fields = (
            'id',
            'text',
            'date',
            'overall_emotion',
            'notes'
        )
        read_only_fields = (
            'id',
            'date',
            'overall_emotion',
            'notes'
        )

    overall_emotion = serializers.CharField(read_only=True)
    notes = NoteSerializer(many=True, read_only=True)


class DayStatsCreateSerializer(serializers.ModelSerializer):
    class Meta:
        model = DayStats
        fields = (
            'id',
            'text',
            'date',
            'user',
            'overall_emotion',
            'notes'
        )
        read_only_fields = (
            'id',
            'overall_emotion',
            'notes'
        )

    user = serializers.HiddenField(default=serializers.CurrentUserDefault())
    overall_emotion = serializers.CharField(read_only=True)
    notes = NoteSerializer(many=True, read_only=True)

    def validate(self, attrs):
        user = attrs.get('user')
        date = attrs.get('date')
        print(user, date)
        try:
            DayStats.objects.get(user=user, date=date)
        except DayStats.DoesNotExist:
            return attrs
        raise serializers.ValidationError("Statistics for this user and day already exist")
